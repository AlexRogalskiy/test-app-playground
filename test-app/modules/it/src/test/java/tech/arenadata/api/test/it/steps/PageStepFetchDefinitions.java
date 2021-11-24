package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.testcontainers.shaded.com.google.common.io.Files;
import tech.arenadata.api.test.assertions.general.Assertions;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_HTML;

/**
 * Page template fetch definitions
 */
public class PageStepFetchDefinitions extends BasePageStepDefinitions {

	private HttpRequestBase request;
	private String url;

	@Given("users want to fetch uploaded page templates")
	public void usersNavigateToUrl() {
		this.url = this.getHttpClientConfigurer().createServerUrl();
	}

	@When("^users get information on the uploaded page templates$")
	public void usersGetInformationOnPageTemplates() {
		this.request = new HttpGet(this.url);
		this.request.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
	}

	@Then("the page templates data is returned {string}")
	public void theRequestedPageTemplateDataIsReturned(final String fileName) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(HttpStatus.SC_OK)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasContent(Files.toString(new File(fileName), UTF_8));
		}
	}

	@Then("fetch operation should fail with status \\({int})")
	public void theServerShouldReturnAFailStatus(final int status) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_HTML.toString());
		}
	}
}
