package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import tech.arenadata.api.test.assertions.factory.Assertions;

import java.io.IOException;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static tech.arenadata.api.test.assertions.factory.Assertions.DEFAULT_REPRESENTATION;
import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_HTML;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.resourceToString;

/**
 * Page template fetch definitions.
 */
public class PageStepFetchDefinitions extends BasePageStepDefinitions {

	private HttpRequestBase request;
	private String url;

	@Given("^users want to fetch uploaded page templates$")
	public void usersPrepareRequestData() {
		this.url = this.getHttpClientConfigurer().createServerUrl();
	}

	@When("^users request information on the uploaded page templates$")
	public void usersRequestInformationOnPageTemplates() {
		this.request = new HttpGet(this.url);
		this.request.addHeader(ACCEPT, APPLICATION_JSON.toString());
	}

	@Then("page templates data {string} should be successfully returned")
	public void theServerShouldReturnASuccessStatus(final String fileName) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.withRepresentation(DEFAULT_REPRESENTATION)
				.hasStatusCode(SC_OK)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasContent(resourceToString("templates", "response", fileName));
		}
	}

	@Then("fetch operation should fail with status \\({int})")
	public void theServerShouldReturnAFailStatus(final int status) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.withRepresentation(DEFAULT_REPRESENTATION)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_HTML.toString());
		}
	}
}
