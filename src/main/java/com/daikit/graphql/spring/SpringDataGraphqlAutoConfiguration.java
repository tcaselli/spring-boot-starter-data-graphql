package com.daikit.graphql.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.daikit.graphql.execution.GQLErrorProcessor;
import com.daikit.graphql.execution.GQLExecutor;
import com.daikit.graphql.spring.web.GQLRequestHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Spring data GraphQL auto configuration
 *
 * @author Thibaut Caselli
 */
@Configuration
@EnableConfigurationProperties(SpringDataGraphqlProperties.class)
public class SpringDataGraphqlAutoConfiguration {

	/**
	 * @return the {@link WebMvcConfigurer}
	 */
	@Bean
	@ConditionalOnMissingBean
	public WebMvcConfigurer webConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addResourceHandlers(final ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/graphiql/**").addResourceLocations("/graphiql/");
			}

			@Override
			public void addCorsMappings(final CorsRegistry registry) {
				registry.addMapping("/**").allowCredentials(true);
			}
		};
	}

	/**
	 * Create default {@link IGQLControllerRequestHandler} if a bean of this
	 * type is not already existing
	 *
	 * @return an {@link IGQLControllerRequestHandler}
	 */
	@Bean
	@ConditionalOnMissingBean
	public IGQLControllerRequestHandler createDefaultRequestHandler() {
		return new GQLDefaultControllerRequestHandler();
	}

	/**
	 * @return the created {@link ObjectMapper} for GraphQL read/write
	 */
	@Bean
	@ConditionalOnMissingBean
	public ObjectMapper createGQLObjectMapper() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// Do not write null values
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper;
	}

	/**
	 * Create a JSON Object writer with pretty printing
	 *
	 * @param objectMapper
	 *            the {@link ObjectMapper}
	 * @return the created {@link ObjectWriter}
	 */
	@Bean
	@ConditionalOnMissingBean
	public ObjectWriter createGraphQLObjectWriter(@Autowired final ObjectMapper objectMapper) {
		return objectMapper.writer(new DefaultPrettyPrinter());
	}

	/**
	 * @return the created {@link GQLErrorProcessor}
	 */
	@Bean
	@ConditionalOnMissingBean
	public GQLErrorProcessor createGQLErrorProcessor() {
		return new GQLErrorProcessor();
	}

	/**
	 * Create the {@link GQLRequestHandler}
	 *
	 * @param gqlObjectMapper
	 *            the {@link ObjectMapper} for GraphQL read/write
	 * @param executor
	 *            the {@link GQLExecutor}
	 * @return the created {@link GQLRequestHandler}
	 */
	@Bean
	@ConditionalOnMissingBean
	public GQLRequestHandler createGQLRequestHandler(@Autowired final ObjectMapper gqlObjectMapper,
			@Autowired final GQLExecutor executor) {
		return new GQLRequestHandler(gqlObjectMapper, executor);
	}

}
