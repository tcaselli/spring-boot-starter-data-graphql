package com.daikit.graphql.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.daikit.graphql.config.GQLSchemaConfig;
import com.daikit.graphql.enums.GQLOrderByDirectionEnum;
/**
 * Spring data GraphQL properties
 *
 * @author Thibaut Caselli
 */
@ConfigurationProperties(prefix = "spring.data.graphql", ignoreUnknownFields = true)
public class SpringDataGraphqlProperties {

	private String queryTypeName;
	private String mutationTypeName;

	private String inputTypeNameSuffix;
	private String outputTypeNameSuffix;

	private String queryGetListOutputTypeNameSuffix;
	private String outputDeleteResultTypeNamePrefix;

	private String queryGetListPrefix;
	private String queryGetByIdPrefix;
	private String mutationSavePrefix;
	private String mutationDeletePrefix;

	private String attributeIdSuffix;
	private String attributePluralSuffix;
	private String attributeIdPluralSuffix;

	private String mutationAttributeInputDataName;
	private String queryGetListAttributeOutputDataName;

	private GQLOrderByDirectionEnum queryGetListFilterAttributeOrderByDirectionDefaultValue;

	private String mutationDeleteResultAttributeId;
	private String mutationDeleteResultAttributeTypename;

	private String queryGetListFilterAttributeName;
	private String queryGetListFilterAttributeOperatorTypeNamePrefix;
	private String queryGetListFilterAttributeOperatorName;
	private String queryGetListFilterAttributeValueName;
	private String queryGetListFilterAttributeOrderByName;
	private String queryGetListFilterAttributeOrderByFieldName;
	private String queryGetListFilterAttributeOrderByDirectionName;
	private String queryGetListPagingAttributeName;
	private String queryGetListPagingAttributeTotalLengthName;
	private String queryGetListPagingAttributeOffsetName;
	private String queryGetListPagingAttributeLimitName;
	private Integer queryGetListPagingAttributeLimitDefaultValue;

	private String queryGetListFilterEntityTypeNameSuffix;

	private String concreteEmbeddedExtendingTypeNamePrefix;

	private String attributeIdName;

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// PUBLIC METHODS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	/**
	 * Initialize given {@link GQLSchemaConfig} from properties
	 *
	 * @param schemaConfig
	 *            the {@link GQLSchemaConfig}
	 */
	public void initializeConfig(GQLSchemaConfig schemaConfig) {
		if (queryTypeName != null) {
			schemaConfig.setQueryTypeName(queryTypeName);
		}
		if (mutationTypeName != null) {
			schemaConfig.setMutationTypeName(mutationTypeName);
		}
		if (inputTypeNameSuffix != null) {
			schemaConfig.setInputTypeNameSuffix(inputTypeNameSuffix);
		}
		if (outputTypeNameSuffix != null) {
			schemaConfig.setOutputTypeNameSuffix(outputTypeNameSuffix);
		}
		if (queryGetListPrefix != null) {
			schemaConfig.setQueryGetListPrefix(queryGetListPrefix);
		}
		if (queryGetByIdPrefix != null) {
			schemaConfig.setQueryGetByIdPrefix(queryGetByIdPrefix);
		}
		if (mutationSavePrefix != null) {
			schemaConfig.setMutationSavePrefix(mutationSavePrefix);
		}
		if (mutationDeletePrefix != null) {
			schemaConfig.setMutationDeletePrefix(mutationDeletePrefix);
		}
		if (attributeIdSuffix != null) {
			schemaConfig.setAttributeIdSuffix(attributeIdSuffix);
		}
		if (attributePluralSuffix != null) {
			schemaConfig.setAttributePluralSuffix(attributePluralSuffix);
		}
		if (attributeIdPluralSuffix != null) {
			schemaConfig.setAttributeIdPluralSuffix(attributeIdPluralSuffix);
		}
		if (mutationAttributeInputDataName != null) {
			schemaConfig.setMutationAttributeInputDataName(mutationAttributeInputDataName);
		}
		if (queryGetListAttributeOutputDataName != null) {
			schemaConfig.setQueryGetListAttributeOutputDataName(queryGetListAttributeOutputDataName);
		}
		if (queryGetListFilterAttributeOrderByDirectionDefaultValue != null) {
			schemaConfig.setQueryGetListFilterAttributeOrderByDirectionDefaultValue(
					queryGetListFilterAttributeOrderByDirectionDefaultValue);
		}
		if (mutationDeleteResultAttributeId != null) {
			schemaConfig.setMutationDeleteResultAttributeId(mutationDeleteResultAttributeId);
		}
		if (mutationDeleteResultAttributeTypename != null) {
			schemaConfig.setMutationDeleteResultAttributeTypename(mutationDeleteResultAttributeTypename);
		}
		if (queryGetListFilterAttributeName != null) {
			schemaConfig.setQueryGetListFilterAttributeName(queryGetListFilterAttributeName);
		}
		if (queryGetListFilterAttributeOperatorTypeNamePrefix != null) {
			schemaConfig.setQueryGetListFilterAttributeOperatorTypeNamePrefix(
					queryGetListFilterAttributeOperatorTypeNamePrefix);
		}
		if (queryGetListFilterAttributeOperatorName != null) {
			schemaConfig.setQueryGetListFilterAttributeOperatorName(queryGetListFilterAttributeOperatorName);
		}
		if (queryGetListFilterAttributeValueName != null) {
			schemaConfig.setQueryGetListFilterAttributeValueName(queryGetListFilterAttributeValueName);
		}
		if (queryGetListFilterAttributeOrderByName != null) {
			schemaConfig.setQueryGetListFilterAttributeOrderByName(queryGetListFilterAttributeOrderByName);
		}
		if (queryGetListFilterAttributeOrderByFieldName != null) {
			schemaConfig.setQueryGetListFilterAttributeOrderByFieldName(queryGetListFilterAttributeOrderByFieldName);
		}
		if (queryGetListFilterAttributeOrderByDirectionName != null) {
			schemaConfig.setQueryGetListFilterAttributeOrderByDirectionName(
					queryGetListFilterAttributeOrderByDirectionName);
		}
		if (queryGetListPagingAttributeName != null) {
			schemaConfig.setQueryGetListPagingAttributeName(queryGetListPagingAttributeName);
		}
		if (queryGetListPagingAttributeTotalLengthName != null) {
			schemaConfig.setQueryGetListPagingAttributeTotalLengthName(queryGetListPagingAttributeTotalLengthName);
		}
		if (queryGetListPagingAttributeOffsetName != null) {
			schemaConfig.setQueryGetListPagingAttributeOffsetName(queryGetListPagingAttributeOffsetName);
		}
		if (queryGetListPagingAttributeLimitName != null) {
			schemaConfig.setQueryGetListPagingAttributeLimitName(queryGetListPagingAttributeLimitName);
		}
		if (queryGetListPagingAttributeLimitDefaultValue != null) {
			schemaConfig.setQueryGetListPagingAttributeLimitDefaultValue(queryGetListPagingAttributeLimitDefaultValue);
		}
		if (queryGetListFilterEntityTypeNameSuffix != null) {
			schemaConfig.setQueryGetListFilterEntityTypeNameSuffix(queryGetListFilterEntityTypeNameSuffix);
		}
		if (concreteEmbeddedExtendingTypeNamePrefix != null) {
			schemaConfig.setConcreteEmbeddedExtendingTypeNamePrefix(concreteEmbeddedExtendingTypeNamePrefix);
		}
		if (attributeIdName != null) {
			schemaConfig.setAttributeIdName(attributeIdName);
		}

	}

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// GETTERS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	/**
	 * @return the queryTypeName
	 */
	public String getQueryTypeName() {
		return queryTypeName;
	}

	/**
	 * @return the mutationTypeName
	 */
	public String getMutationTypeName() {
		return mutationTypeName;
	}

	/**
	 * @return the inputTypeNameSuffix
	 */
	public String getInputTypeNameSuffix() {
		return inputTypeNameSuffix;
	}

	/**
	 * @return the outputTypeNameSuffix
	 */
	public String getOutputTypeNameSuffix() {
		return outputTypeNameSuffix;
	}

	/**
	 * @return the queryGetListOutputTypeNameSuffix
	 */
	public String getQueryGetListOutputTypeNameSuffix() {
		return queryGetListOutputTypeNameSuffix;
	}

	/**
	 * @return the outputDeleteResultTypeNamePrefix
	 */
	public String getOutputDeleteResultTypeNamePrefix() {
		return outputDeleteResultTypeNamePrefix;
	}

	/**
	 * @return the queryGetListPrefix
	 */
	public String getQueryGetListPrefix() {
		return queryGetListPrefix;
	}

	/**
	 * @return the queryGetByIdPrefix
	 */
	public String getQueryGetByIdPrefix() {
		return queryGetByIdPrefix;
	}

	/**
	 * @return the mutationSavePrefix
	 */
	public String getMutationSavePrefix() {
		return mutationSavePrefix;
	}

	/**
	 * @return the mutationDeletePrefix
	 */
	public String getMutationDeletePrefix() {
		return mutationDeletePrefix;
	}

	/**
	 * @return the attributeIdSuffix
	 */
	public String getAttributeIdSuffix() {
		return attributeIdSuffix;
	}

	/**
	 * @return the attributePluralSuffix
	 */
	public String getAttributePluralSuffix() {
		return attributePluralSuffix;
	}

	/**
	 * @return the attributeIdPluralSuffix
	 */
	public String getAttributeIdPluralSuffix() {
		return attributeIdPluralSuffix;
	}

	/**
	 * @return the mutationAttributeInputDataName
	 */
	public String getMutationAttributeInputDataName() {
		return mutationAttributeInputDataName;
	}

	/**
	 * @return the queryGetListAttributeOutputDataName
	 */
	public String getQueryGetListAttributeOutputDataName() {
		return queryGetListAttributeOutputDataName;
	}

	/**
	 * @return the queryGetListFilterAttributeOrderByDirectionDefaultValue
	 */
	public GQLOrderByDirectionEnum getQueryGetListFilterAttributeOrderByDirectionDefaultValue() {
		return queryGetListFilterAttributeOrderByDirectionDefaultValue;
	}

	/**
	 * @return the mutationDeleteResultAttributeId
	 */
	public String getMutationDeleteResultAttributeId() {
		return mutationDeleteResultAttributeId;
	}

	/**
	 * @return the mutationDeleteResultAttributeTypename
	 */
	public String getMutationDeleteResultAttributeTypename() {
		return mutationDeleteResultAttributeTypename;
	}

	/**
	 * @return the queryGetListFilterAttributeName
	 */
	public String getQueryGetListFilterAttributeName() {
		return queryGetListFilterAttributeName;
	}

	/**
	 * @return the queryGetListFilterAttributeOperatorTypeNamePrefix
	 */
	public String getQueryGetListFilterAttributeOperatorTypeNamePrefix() {
		return queryGetListFilterAttributeOperatorTypeNamePrefix;
	}

	/**
	 * @return the queryGetListFilterAttributeOperatorName
	 */
	public String getQueryGetListFilterAttributeOperatorName() {
		return queryGetListFilterAttributeOperatorName;
	}

	/**
	 * @return the queryGetListFilterAttributeValueName
	 */
	public String getQueryGetListFilterAttributeValueName() {
		return queryGetListFilterAttributeValueName;
	}

	/**
	 * @return the queryGetListFilterAttributeOrderByName
	 */
	public String getQueryGetListFilterAttributeOrderByName() {
		return queryGetListFilterAttributeOrderByName;
	}

	/**
	 * @return the queryGetListFilterAttributeOrderByFieldName
	 */
	public String getQueryGetListFilterAttributeOrderByFieldName() {
		return queryGetListFilterAttributeOrderByFieldName;
	}

	/**
	 * @return the queryGetListFilterAttributeOrderByDirectionName
	 */
	public String getQueryGetListFilterAttributeOrderByDirectionName() {
		return queryGetListFilterAttributeOrderByDirectionName;
	}

	/**
	 * @return the queryGetListPagingAttributeName
	 */
	public String getQueryGetListPagingAttributeName() {
		return queryGetListPagingAttributeName;
	}

	/**
	 * @return the queryGetListPagingAttributeTotalLengthName
	 */
	public String getQueryGetListPagingAttributeTotalLengthName() {
		return queryGetListPagingAttributeTotalLengthName;
	}

	/**
	 * @return the queryGetListPagingAttributeOffsetName
	 */
	public String getQueryGetListPagingAttributeOffsetName() {
		return queryGetListPagingAttributeOffsetName;
	}

	/**
	 * @return the queryGetListPagingAttributeLimitName
	 */
	public String getQueryGetListPagingAttributeLimitName() {
		return queryGetListPagingAttributeLimitName;
	}

	/**
	 * @return the queryGetListPagingAttributeLimitDefaultValue
	 */
	public int getQueryGetListPagingAttributeLimitDefaultValue() {
		return queryGetListPagingAttributeLimitDefaultValue;
	}

	/**
	 * @return the queryGetListFilterEntityTypeNameSuffix
	 */
	public String getQueryGetListFilterEntityTypeNameSuffix() {
		return queryGetListFilterEntityTypeNameSuffix;
	}

	/**
	 * @return the concreteEmbeddedExtendingTypeNamePrefix
	 */
	public String getConcreteEmbeddedExtendingTypeNamePrefix() {
		return concreteEmbeddedExtendingTypeNamePrefix;
	}

	/**
	 * @return the attributeIdName
	 */
	public String getAttributeIdName() {
		return attributeIdName;
	}

}
