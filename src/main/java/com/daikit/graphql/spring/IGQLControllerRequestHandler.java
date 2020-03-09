package com.daikit.graphql.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request handler for GraphQL controller
 *
 * @author Thibaut Caselli
 */
public interface IGQLControllerRequestHandler {

	/**
	 * Get GraphQL schema instrospection
	 * 
	 * @param request
	 *            the {@link HttpServletRequest}
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	void handleIntropsectionRequest(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Get schema instrospection fragments for client GraphQL engine
	 * initialization (like Apollo for example)
	 * 
	 * @param request
	 *            the {@link HttpServletRequest}
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	void handleIntrospectionFragmentsRequest(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Run a query/mutation against GraphQL engine
	 *
	 * @param request
	 *            the {@link HttpServletRequest}
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	void handleQueryRequest(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Handle error happening during GraphQL request
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 * @param e
	 *            the Exception to handle
	 */
	void handleError(HttpServletResponse response, Exception e);

}
