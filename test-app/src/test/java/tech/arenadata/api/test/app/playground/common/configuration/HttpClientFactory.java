package tech.arenadata.api.test.app.playground.common.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static java.lang.Math.toIntExact;

/**
 * Http client factory
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpClientFactory {

	/**
	 * Http client factory default instance
	 */
	private static final HttpClientFactory INSTANCE = new HttpClientFactory();

	/**
	 * Returns default {@link HttpClientFactory} instance
	 *
	 * @return http client factory
	 */
	public static HttpClientFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns {@link HttpClient} instance.
	 *
	 * @return http client
	 */
	public HttpClient createHttpClient() {
		return HttpClientBuilder.create()
			.setDefaultRequestConfig(this.getRequestConfig())
			.setDefaultSocketConfig(this.getSocketConfig())
			.build();
	}

	/**
	 * Returns secure {@link HttpClient} instance.
	 *
	 * @return https client
	 * @throws KeyStoreException        key store exception
	 * @throws NoSuchAlgorithmException no such algorithm exception
	 * @throws KeyManagementException   key management exception
	 */
	public CloseableHttpClient createHttpsClient()
		throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		final var sslContext = new SSLContextBuilder()
			.loadTrustMaterial(null, (certificate, authType) -> true)
			.build();

		return resolveProxySetting(HttpClients.custom())
			.setSSLContext(sslContext)
			.setSSLHostnameVerifier(new NoopHostnameVerifier())
			.setDefaultRequestConfig(this.getRequestConfig())
			.build();
	}

	/**
	 * Returns {@link String} collection of http proxy parameters.
	 *
	 * @param httpProxy initial input {@link String} http proxy value
	 * @return resolved http proxy values; first is host(@nonNull), second is username(@nullable)
	 * , third is password(@nullable)
	 */
	private String[] resolveHttpProxy(final String httpProxy) {
		final var proxyUri = URI.create(httpProxy);
		int port = proxyUri.getPort();
		if (port == -1) {
			if (Objects.equals("http", proxyUri.getScheme())) {
				port = 80;
			}
			if (Objects.equals("https", proxyUri.getScheme())) {
				port = 443;
			}
		}

		final var hostUrl = proxyUri.getScheme() + "://" + proxyUri.getHost() + ":" + port;
		final var usernamePassword = proxyUri.getUserInfo();
		if (StringUtils.isNotBlank(usernamePassword)) {
			final var atColon = usernamePassword.indexOf(':');
			final var username = (atColon >= 0) ? usernamePassword.substring(0, atColon) : usernamePassword;
			final var password = (atColon >= 0) ? usernamePassword.substring(atColon + 1) : null;

			return new String[]{hostUrl, username, password};
		}

		return new String[]{hostUrl};
	}

	/**
	 * Resolves system proxy configuration.
	 *
	 * @param httpClientBuilder initial input {@link HttpClientBuilder} to operate by
	 * @return http client builder with configured proxy
	 */
	private HttpClientBuilder resolveProxySetting(final HttpClientBuilder httpClientBuilder) {
		final var httpProxyEnv = System.getenv("http_proxy");
		if (StringUtils.isNotBlank(httpProxyEnv)) {
			final var httpProxy = resolveHttpProxy(httpProxyEnv);
			final var httpHost = HttpHost.create(httpProxy[0]);
			httpClientBuilder.setProxy(httpHost);

			if (httpProxy.length == 3) {
				//set proxy credentials
				final var credentialsProvider = new BasicCredentialsProvider();
				credentialsProvider
					.setCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort()),
						new UsernamePasswordCredentials(httpProxy[1], httpProxy[2]));
				httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
			}
		}

		return httpClientBuilder;
	}

	/**
	 * Returns {@link SocketConfig} instance.
	 *
	 * @return socket config
	 */
	private SocketConfig getSocketConfig() {
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
	private RequestConfig getRequestConfig() {
		final var config = ConfigurationFactory.getInstance();
		return RequestConfig.custom()
			.setConnectTimeout(toIntExact(config.getConnectTimeout().toMillis()))
			.setConnectionRequestTimeout(toIntExact(config.getConnectionRequestTimeout().toMillis()))
			.setSocketTimeout(toIntExact(config.getSocketTimeout().toMillis()))
			.build();
	}
}
