package com.daikit.graphql.spring.web;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.daikit.graphql.data.output.GQLExecutionResult;
import com.daikit.graphql.exception.GQLException;
import com.daikit.graphql.utils.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilities for {@link GQLRequestHandler}
 * 
 * @author Thibaut Caselli
 */
public class GQLIOUtils {

	/**
	 * Read given {@link InputStream} as String
	 *
	 * @param inputStream
	 *            the {@link InputStream}
	 * @return the
	 */
	public static String readInputStream(final InputStream inputStream) {
		final Scanner scanner = new Scanner(inputStream);
		scanner.useDelimiter("\\A");
		final String read = scanner.hasNext() ? scanner.next() : "";
		scanner.close();
		return read;
	}

	/**
	 * Write given result as JSON in given response
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 * @param objectMapper
	 *            the {@link ObjectMapper}
	 * @param result
	 *            the {@link GQLExecutionResult}
	 */
	public static void writeInResponse(final HttpServletResponse response, final ObjectMapper objectMapper,
			final GQLExecutionResult result) {
		try {
			writeInResponse(response, objectMapper.writeValueAsString(result));
		} catch (final IOException e) {
			throw new GQLException(Message.format("An error happened while writing response. [{}]", e.getMessage()), e);
		}
	}

	/**
	 * Write given string content in servlet response
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 * @param content
	 *            the content to be written
	 */
	public static void writeInResponse(final HttpServletResponse response, final String content) {
		final byte[] bytes = content.getBytes();
		response.setContentType(MediaType.APPLICATION_JSON.toString());
		response.setContentLength(bytes.length);
		response.setBufferSize(bytes.length);
		setCacheExpireDate(response, 0);
		setNotCacheable(response); // TODO disabled caching here
		try {
			response.getOutputStream().write(bytes);
		} catch (final IOException e) {
			throw new GQLException(Message.format("An error happened while writing response. [{}]", e.getMessage()), e);
		}
	}

	/**
	 * Set cache expiration date in servlet response
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 * @param seconds
	 *            the delay in seconds before cache expiration
	 */
	public static void setCacheExpireDate(final HttpServletResponse response, final int seconds) {
		if (response != null) {
			final long nowPlusExpiration = new Date().getTime() + Long.valueOf(seconds) * 1000l;
			response.setHeader("Cache-Control", "PUBLIC, max-age=" + seconds);
			final DateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
			httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			response.setHeader("Expires", httpDateFormat.format(nowPlusExpiration));
		}
	}

	/**
	 * Set a response not cacheable
	 *
	 * @param response
	 *            the {@link HttpServletResponse}
	 */
	public static void setNotCacheable(final HttpServletResponse response) {
		if (response != null) {
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("X-Cacheable", "NO: !obj.cacheable");
		}
	}
}
