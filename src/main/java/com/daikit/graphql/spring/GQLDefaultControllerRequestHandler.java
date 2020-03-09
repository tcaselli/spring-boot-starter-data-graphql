package com.daikit.graphql.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.daikit.graphql.data.output.GQLExecutionResult;
import com.daikit.graphql.exception.GQLException;
import com.daikit.graphql.execution.GQLErrorProcessor;
import com.daikit.graphql.spring.web.GQLIOUtils;
import com.daikit.graphql.spring.web.GQLRequestHandler;
import com.daikit.graphql.utils.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Default implementation for {@link IGQLControllerRequestHandler}
 *
 * @author Thibaut Caselli
 */
public class GQLDefaultControllerRequestHandler implements IGQLControllerRequestHandler {

	@Autowired
	private GQLErrorProcessor gqlErrorProcessor;
	@Autowired
	private GQLRequestHandler gqlRequestHandler;
	@Autowired
	private ObjectWriter gqlObjectWriter;

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// METHODS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@Override
	public void handleIntropsectionRequest(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			gqlRequestHandler.handleIntrospectionRequest(request, response);
		} catch (final Exception e) {
			handleError(response, e);
		}
	}

	@Override
	public void handleIntrospectionFragmentsRequest(final HttpServletRequest request,
			final HttpServletResponse response) {
		try {
			gqlRequestHandler.handleIntrospectionFragmentsRequest(request, response);
		} catch (final Exception e) {
			handleError(response, e);
		}
	}

	@Override
	public void handleQueryRequest(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			gqlRequestHandler.handleRequest(request, response);
		} catch (final Exception e) {
			handleError(response, e);
		}
	}

	@Override
	public void handleError(final HttpServletResponse response, final Exception e) {
		try {
			GQLIOUtils.writeInResponse(response,
					gqlObjectWriter.writeValueAsString(new GQLExecutionResult(gqlErrorProcessor.handleError(e))));
		} catch (final JsonProcessingException e1) {
			throw new GQLException(Message.format("An error happened while writing error result {}", e1.getMessage()),
					e1);
		}
	}

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// PROTECTED CALLBACK METHODS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

}
