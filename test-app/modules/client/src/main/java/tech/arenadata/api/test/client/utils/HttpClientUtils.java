package tech.arenadata.api.test.client.utils;

import lombok.experimental.UtilityClass;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import java.io.File;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Http client utilities for accessing remote apis
 */
@UtilityClass
public class HttpClientUtils {

	/**
	 * Default http headers
	 */
	private static final Header[] NO_CACHE_HEADERS = {
		new BasicHeader(HttpHeaders.PRAGMA, "no-cache"),
		new BasicHeader(HttpHeaders.EXPIRES, "0"),
		new BasicHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
	};

	/**
	 * Returns {@link HttpGet} request by input parameters
	 *
	 * @param endpointUrl initial input {@link String} endpoint url
	 * @param headers     initial input {@link Header} collection
	 * @return http get request
	 */
	public static HttpGet createGetRequest(final String endpointUrl, final Header... headers) {
		final var request = new HttpGet(endpointUrl);
		request.setHeaders(NO_CACHE_HEADERS);
		Stream.ofNullable(headers).forEach(request::setHeaders);

		return request;
	}

	/**
	 * Returns {@link HttpPost} request by input parameters
	 *
	 * @param endpointUrl initial input {@link String} endpoint url
	 * @param data        initial input {@link String} data
	 * @param headers     initial input {@link Header} collection
	 * @return http post request
	 */
	public static HttpPost createPostDataRequest(final String endpointUrl,
												 final String data,
												 final Header... headers) {
		final var request = new HttpPost(endpointUrl);
		Stream.ofNullable(headers).forEach(request::setHeaders);

		final var entity = new StringEntity(data, UTF_8);
		request.setEntity(entity);

		return request;
	}

	/**
	 * Returns {@link HttpPost} file request by input parameters
	 *
	 * @param endpointUrl initial input {@link String} endpoint url
	 * @param filename    initial input {@link String} file name
	 * @param headers     initial input {@link Header} collection
	 * @return http post request
	 */
	public static HttpPost createPostFileRequest(final String endpointUrl,
												 final String filename,
												 final Header... headers) {
		final var request = new HttpPost(endpointUrl);
		Stream.ofNullable(headers).forEach(request::setHeaders);

		final var entity = new FileEntity(new File(filename));
		request.setEntity(entity);

		return request;
	}
}
