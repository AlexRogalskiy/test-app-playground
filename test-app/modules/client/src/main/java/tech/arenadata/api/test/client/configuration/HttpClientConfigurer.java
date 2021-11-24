package tech.arenadata.api.test.client.configuration;

import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import tech.arenadata.api.test.commons.factory.ConfigurationFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;
import static org.apache.http.client.utils.URLEncodedUtils.parsePathSegments;

/**
 * Http client service.
 */
public class HttpClientConfigurer {

	/**
	 * Returns {@link URL} server by provided configuration.
	 *
	 * @param pathSegments initial input {@link String} collection of url path segments to operate by
	 * @return server url
	 */
	@SneakyThrows
	public String createServerUrl(final String... pathSegments) {
		final var config = ConfigurationFactory.getInstance();
		final var paths = Stream.concat(
			parsePathSegments(config.getServerPath()).stream(),
			Arrays.stream(pathSegments)
		).collect(Collectors.toList());

		return new URIBuilder()
			.setScheme(config.getServerScheme())
			.setHost(config.getServerHost())
			.setPort(config.getServerPort())
			.setPathSegments(paths)
			.build()
			.toString();
	}

	/**
	 * Returns {@link SocketConfig} instance by provided configuration.
	 *
	 * @return socket config
	 */
	public SocketConfig createSocketConfig() {
		final var config = ConfigurationFactory.getInstance();
		return SocketConfig.custom()
			.setSoTimeout(toIntExact(config.getSocketTimeout().toMillis()))
			.build();
	}

	/**
	 * Returns {@link RequestConfig} instance by provided configuration.
	 *
	 * @return request config
	 */
	public RequestConfig createRequestConfig() {
		final var config = ConfigurationFactory.getInstance();
		return RequestConfig.custom()
			.setConnectTimeout(toIntExact(config.getConnectTimeout().toMillis()))
			.setConnectionRequestTimeout(toIntExact(config.getConnectionRequestTimeout().toMillis()))
			.setSocketTimeout(toIntExact(config.getSocketTimeout().toMillis()))
			.build();
	}

	/**
	 * Returns {@link HttpClientContext} instance by collection of attributes.
	 *
	 * @param attributes initial input {@link Pair} collection of attributes to operate by
	 * @return http client context
	 */
	@SafeVarargs
	public final HttpClientContext createClientContext(final Pair<String, Object>... attributes) {
		final var config = ConfigurationFactory.getInstance();
		final var context = HttpClientContext.create();
		context.setTargetHost(HttpHost.create(config.getServerHost()));
		Stream.ofNullable(attributes)
			.flatMap(Arrays::stream)
			.forEach(attr -> context.setAttribute(attr.getKey(), attr.getValue()));

		return context;
	}
}
