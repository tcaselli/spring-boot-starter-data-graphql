package com.daikit.graphql.spring.web;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * GraphQL input data
 *
 * @author tcaselli
 * @version $Revision$ Last modifier: $Author$ Last commit: $Date$
 */
public class GQLRequestInputData {

	private JsonNode query;
	private String operationName;
	private JsonNode variables;

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// GETTERS / SETTERS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	/**
	 * @return the query
	 */
	public JsonNode getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(final JsonNode query) {
		this.query = query;
	}

	/**
	 * @return the operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * @param operationName
	 *            the operationName to set
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * @return the variables
	 */
	public JsonNode getVariables() {
		return variables;
	}

	/**
	 * @param variables
	 *            the variables to set
	 */
	public void setVariables(final JsonNode variables) {
		this.variables = variables;
	}

}
