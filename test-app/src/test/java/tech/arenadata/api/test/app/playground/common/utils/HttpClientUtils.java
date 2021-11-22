package tech.arenadata.api.test.app.playground.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Http client utilities for accessing rest api
 */
@UtilityClass
public class HttpClientUtils {

	private static final String DEFAULT_USER_AGENT = "test-api";

	/**
	 * Returns {@link HttpGet} request by input parameters
	 *
	 * @param endpointUrl initial input {@link String} endpoint url
	 * @return http get request
	 */
	public static HttpGet createGetRequest(final String endpointUrl) {
		final var request = new HttpGet(endpointUrl);
		request.setHeader(HttpHeaders.USER_AGENT, DEFAULT_USER_AGENT);

		return request;
	}

	/**
	 * Returns {@link HttpPost} request by input parameters
	 *
	 * @param endpointUrl initial input {@link String} endpoint url
	 * @param data        initial input {@link String} data
	 * @return http post request
	 */
	public static HttpPost createPostRequest(final String endpointUrl,
											 final String data,
											 final ContentType contentType) {
		final var request = new HttpPost(endpointUrl);
		request.setHeader(HttpHeaders.USER_AGENT, DEFAULT_USER_AGENT);
		request.addHeader(HttpHeaders.CONTENT_TYPE, contentType.toString());

		final var entity = new StringEntity(data, UTF_8);
		request.setEntity(entity);

		return request;
	}
}
