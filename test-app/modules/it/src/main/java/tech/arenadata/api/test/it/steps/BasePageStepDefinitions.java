package tech.arenadata.api.test.it.steps;

import org.apache.http.impl.client.CloseableHttpClient;
import tech.arenadata.api.test.client.configuration.HttpClientConfigurer;
import tech.arenadata.api.test.client.configuration.HttpClientFactory;

/**
 * Base page step definitions.
 */
public class BasePageStepDefinitions {

	private final HttpClientConfigurer httpClientConfigurer;
	private final CloseableHttpClient httpClient;

	public BasePageStepDefinitions() {
		this.httpClientConfigurer = new HttpClientConfigurer();
		this.httpClient = HttpClientFactory.createHttpClient(this.httpClientConfigurer);
	}

	public CloseableHttpClient getHttpClient() {
		return this.httpClient;
	}

	public HttpClientConfigurer getHttpClientConfigurer() {
		return this.httpClientConfigurer;
	}
}
