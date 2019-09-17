package com.daikit.graphql.spring.web;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.daikit.graphql.data.output.GQLExecutionResult;
import com.daikit.graphql.exception.GQLException;
import com.daikit.graphql.execution.GQLExecutor;
import com.daikit.graphql.introspection.GQLIntrospection;
import com.daikit.graphql.utils.Assert;
import com.daikit.graphql.utils.Message;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * Request handler
 *
 * @author tcaselli
 * @version $Revision$ Last modifier: $Author$ Last commit: $Date$
 */
public class GQLRequestHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	// The new line character for current file system
	private final String NEWLINE = System.getProperty("line.separator");

	/**
	 * GQL Filename prefix
	 */
	public static String GQL_FILENAME_PREFIX = "TMP_";

	private final ObjectMapper objectMapper;
	private final GQLExecutor executor;
	private final ObjectMapper loggerMapper = new ObjectMapper();

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// CONSTRUCTORS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	/**
	 * Constructor
	 *
	 * @param objectMapper
	 *            the {@link ObjectMapper} for writing responses
	 * @param executor
	 *            the {@link GQLExecutor} for executing queries/mutations
	 */
	public GQLRequestHandler(final ObjectMapper objectMapper, final GQLExecutor executor) {
		this.objectMapper = objectMapper;
		this.executor = executor;
	}

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// METHODS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	/**
	 * Handle introspection request
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	public void handleIntrospectionRequest(final HttpServletResponse response) {
		GQLIOUtils.writeInResponse(response, objectMapper,
				GQLIntrospection.getAllTypes(query -> executor.execute(query)));
	}

	/**
	 * Handle introspection fragments request for Apollo graphQL engine
	 * initialization
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	public void handleIntrospectionFragmentsRequest(final HttpServletResponse response) {
		final GQLExecutionResult result = GQLIntrospection.getFragments(query -> executor.execute(query));
		final Map<String, Object> resultSchema = map(map(result.getData()).get("__schema"));
		resultSchema.put("types", list(resultSchema.get("types")).stream()
				.filter(map -> map.get("possibleTypes") != null).collect(Collectors.toList()));
		GQLIOUtils.writeInResponse(response, objectMapper, result);
	}

	/**
	 * Handle request and write result to response
	 *
	 * @param request
	 *            the {@link HttpServletRequest}
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	public void handleRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final String requestBody;

		final boolean multipart = ServletFileUpload.isMultipartContent(request);
		if (multipart) {
			requestBody = request.getParameterMap().get("operations")[0];
		} else {
			try {
				requestBody = GQLIOUtils.readInputStream(request.getInputStream());
			} catch (final IOException e) {
				throw new GQLException(
						Message.format("An error happened while reading the request. [{}]", e.getMessage()), e);
			}
		}
		GQLExecutionResult result = null;
		GQLRequestInputData inputData;
		final Map<String, Object> variables;
		try {
			inputData = objectMapper.readValue(requestBody, GQLRequestInputData.class);
			// variables is either null or TextNode or ObjectNode
			variables = readVariables(inputData, request, multipart);
		} catch (final IOException e) {
			throw new GQLException(
					Message.format("An error happened while reading request body to JSON. [{}]", e.getMessage()), e);
		}
		result = executor.execute(sanitize(inputData.getQuery().asText()), inputData.getOperationName(), null,
				variables);
		if (result.getErrorDetails() != null) {
			try {
				debugError(result);
			} catch (final JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		GQLIOUtils.writeInResponse(response, objectMapper, result);
	}

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// PRIVATE UTILS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	@SuppressWarnings("unchecked")
	private Map<String, Object> map(final Object object) {
		return (Map<String, Object>) object;
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> list(final Object object) {
		return (List<Map<String, Object>>) object;
	}

	private void debugError(final GQLExecutionResult result) throws JsonProcessingException {
		logger.error(Message.format("An error happened while running GQL Query : type [{}] message [{}] errors [{}]",
				result.getErrorDetails().getType(), result.getErrorDetails().getMessage(),
				toJSONForLog(result.getErrors())));
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> readVariables(final GQLRequestInputData inputData, final HttpServletRequest request,
			final boolean multipart) throws IOException, JsonParseException, JsonMappingException {
		Map<String, Object> variables = null;
		if (inputData.getVariables() != null) {
			if (inputData.getVariables() instanceof TextNode) {
				final String asText = inputData.getVariables().asText();
				variables = StringUtils.isNotEmpty(asText)
						? objectMapper.readValue(inputData.getVariables().asText(), Map.class)
						: Collections.emptyMap();
			} else {
				variables = objectMapper.convertValue(inputData.getVariables(), Map.class);
			}
		}
		if (variables == null) {
			variables = Collections.emptyMap();
		}
		if (multipart) {
			updateVariableFromMultipart(request, variables);
		}
		return variables;
	}

	@SuppressWarnings("unchecked")
	private void updateVariableFromMultipart(final HttpServletRequest request, final Map<String, Object> variables)
			throws IOException {
		final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Assert.assertTrue(!multipartRequest.getMultiFileMap().isEmpty(), "Multipart request should contain file(s).");
		final Map<String, List<String>> fileMappings = splitFileMappings(request.getParameterMap().get("map")[0]);
		final Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		// Each item of filesMappings corresponds to a path within variables
		// where there is a file to be filled
		// The file which will be used to fill the field within variables at
		// this path is contained in fileMap at same index

		// For example :

		// variables =
		// [{"prop1":[{"prop2":[{"file":null},{"file":null}]}]},{"prop1":[{"prop2":[{"file":null}]}]}]
		// fileMappings =
		// [["prop1","0","prop2","0","file"],["prop1","0","prop2","1","file"],["prop1","1","prop2","0","file"]]
		// fileMap = {"0":multipartFile0,"1":multipartFile1,"2":multipartFile2}

		// Let's set files in variables
		for (final Entry<String, List<String>> fileMappingEntry : fileMappings.entrySet()) {
			final String fileMappingKey = fileMappingEntry.getKey();
			final List<String> fileMappingPath = fileMappingEntry.getValue();
			// First we need to find the variable embedded property where to set
			// or add the file
			Object varProperty = variables;
			for (int j = 0; j < fileMappingPath.size() - 1; j++) {
				final String currentPathFragment = fileMappingPath.get(j);
				if (varProperty instanceof Map) {
					varProperty = ((Map<String, Object>) varProperty).get(currentPathFragment);
				} else if (varProperty instanceof List) {
					varProperty = ((List<Object>) varProperty).get(Integer.parseInt(currentPathFragment));
				}
			}

			// Create temporary file
			final MultipartFile multipartFile = fileMap.get(fileMappingKey);
			final File temporaryFile = File.createTempFile(GQL_FILENAME_PREFIX,
					GQLSpringConstants.TEMP_FILE_NAME_SEPARATOR + multipartFile.getOriginalFilename());
			multipartFile.transferTo(temporaryFile);

			// Then lets set or add the file in the varProperty
			final String currentPathFragment = fileMappingPath.get(fileMappingPath.size() - 1);
			if (varProperty instanceof Map) {
				((Map<String, Object>) varProperty).put(currentPathFragment, temporaryFile);
			} else if (varProperty instanceof List) {
				((List<Object>) varProperty).set(Integer.parseInt(currentPathFragment), temporaryFile);
			}
		}
	}

	private String sanitize(final String inputDataQuery) {
		return inputDataQuery.replace("\\r", "").replace("\\n", NEWLINE).replace("\\t", "   ").replace("\\\"", "\"");
	}

	private Map<String, List<String>> splitFileMappings(final String map) {
		// Split a String of this style
		// {"0":["variables.files.0"],"1":["variables.files.1"]}
		// And build the returned list ["0"=>["files", "0"], "1"=>["files",
		// "1"]]
		final Map<String, List<String>> ret = new HashMap<>();
		final String[] mapSplits = map.substring(1, map.length() - 1).split(",");
		for (final String mapSplit : mapSplits) {
			final String[] entrySplits = mapSplit.split(":");
			// Remove double quotes
			final String key = entrySplits[0].substring(1, entrySplits[0].length() - 1);
			// Remove square brackets and double quotes
			final String baseValue = entrySplits[1].substring(2, entrySplits[1].length() - 2);
			final List<String> value = Stream.of(StringUtils.split(baseValue.substring("variables.".length()), "."))
					.collect(Collectors.toList());
			ret.put(key, value);
		}
		return ret;
	}

	/**
	 * Write given object as JSON using loggerMapper
	 *
	 * @param value
	 *            the value to be converted to JSON
	 * @return the JSON string
	 * @throws JsonProcessingException
	 */
	private String toJSONForLog(final Object value) throws JsonProcessingException {
		return loggerMapper.writeValueAsString(value);
	}

}
