package com.daikit.graphql.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * GRaphQL simple controller
 *
 * @author tcaselli
 * @version $Revision$ Last modifier: $Author$ Last commit: $Date$
 */
@Controller
@ConditionalOnClass(IGQLControllerRequestHandler.class)
public class GQLController {

	@Autowired
	private IGQLControllerRequestHandler requestHandler;

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// METHODS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	/**
	 * Get GraphQL schema instrospection
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	@RequestMapping(value = "/graphql/introspection", produces = MediaType.APPLICATION_JSON_VALUE)
	public void introspection(final HttpServletResponse response) {
		try {
			requestHandler.handleIntropsectionRequest(response);
		} catch (final Exception e) {
			requestHandler.handleError(response, e);
		}
	}

	/**
	 * Get schema instrospection fragments for client GraphQL engine
	 * initialization (like Apollo for example)
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	@RequestMapping(value = "/graphql/introspectionfragments", produces = MediaType.APPLICATION_JSON_VALUE)
	public void introspectionFragments(final HttpServletResponse response) {
		try {
			requestHandler.handleIntrospectionFragmentsRequest(response);
		} catch (final Exception e) {
			requestHandler.handleError(response, e);
		}
	}

	/**
	 * Run a query/mutation against GraphQL engine
	 *
	 * @param request
	 *            the {@link HttpServletRequest}
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	@RequestMapping(value = "/graphql", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	public void query(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			requestHandler.handleQueryRequest(request, response);
		} catch (final Exception e) {
			requestHandler.handleError(response, e);
		}
	}

}
