package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import tech.arenadata.api.test.assertions.general.Assertions;

import java.io.IOException;

import static java.lang.String.format;
import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * Page template delete definitions
 */
public class PageStepDeleteDefinitions extends BasePageStepDefinitions {

	private HttpRequestBase request;
	private String url;

	@Given("users want to delete uploaded page template {string}")
	public void usersNavigateToUrl(final String templateId) {
		this.url = this.getHttpClientConfigurer().createServerUrl(templateId);
	}

	@When("^users delete information on the uploaded page template$")
	public void usersDeleteInformationOnPageTemplate() {
		this.request = new HttpDelete(this.url);
		this.request.addHeader(ACCEPT, APPLICATION_JSON.toString());
	}

	@Then("the page template {string} is deleted")
	public void theServerShouldReturnASuccessStatus(final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(SC_OK)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("Template with tmpl_id=%s successfully deleted!", templateId));
		}
	}

	@Then("delete operation should fail with page template {string} not found")
	public void theServerShouldReturnANotFoundStatus(final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(SC_NOT_FOUND)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("No template with tmpl_id=%s found!", templateId));
		}
	}
}
