package tech.arenadata.api.test.client.configuration;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import tech.arenadata.api.test.commons.factory.ConfigurationFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.Math.toIntExact;

/**
 * Http client configuration
 */
public class HttpClientConfiguration {

	/**
	 * Returns {@link URL} server.
	 *
	 * @return server url
	 */
	public URL getServerUrl() throws URISyntaxException, MalformedURLException {
		final var config = ConfigurationFactory.getInstance();
		return new URIBuilder()
			.setScheme("http")
			.setHost(config.getServerHost())
			.setPort(config.getServerPort())
			.setPath(config.getServerPath())
			.build()
			.toURL();
	}

	/**
	 * Returns {@link SocketConfig} instance.
	 *
	 * @return socket config
	 */
	public SocketConfig getSocketConfig() {
		final var config = ConfigurationFactory.getInstance();
		return SocketConfig.custom()
			.setSoTimeout(toIntExact(config.getSocketTimeout().toMillis()))
			.build();
	}

	/**
	 * Returns {@link RequestConfig} instance.
	 *
	 * @return request config
	 */
	public RequestConfig getRequestConfig() {
		final var config = ConfigurationFactory.getInstance();
		return RequestConfig.custom()
			.setConnectTimeout(toIntExact(config.getConnectTimeout().toMillis()))
			.setConnectionRequestTimeout(toIntExact(config.getConnectionRequestTimeout().toMillis()))
			.setSocketTimeout(toIntExact(config.getSocketTimeout().toMillis()))
			.build();
	}
}
