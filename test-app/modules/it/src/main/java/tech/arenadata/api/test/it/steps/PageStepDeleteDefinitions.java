package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import tech.arenadata.api.test.assertions.factory.Assertions;

import java.io.IOException;

import static java.lang.String.format;
import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static tech.arenadata.api.test.assertions.factory.Assertions.DEFAULT_REPRESENTATION;

/**
 * Page template delete definitions.
 */
public class PageStepDeleteDefinitions extends BasePageStepDefinitions {

	private HttpRequestBase request;
	private String url;

	@Given("users want to delete uploaded page template with id {string}")
	public void usersPrepareUrl(final String templateId) {
		this.url = this.getHttpClientConfigurer().createServerUrl(templateId);
	}

	@When("^users delete information on the uploaded page template$")
	public void usersDeleteInformationOnPageTemplate() {
		this.request = new HttpDelete(this.url);
		this.request.addHeader(ACCEPT, APPLICATION_JSON.toString());
	}

	@Then("page template with id {string} should be successfully deleted")
	public void theServerShouldReturnASuccessStatus(final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.withRepresentation(DEFAULT_REPRESENTATION)
				.hasStatusCode(SC_OK)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("Template with tmpl_id=%s successfully deleted!", templateId));
		}
	}

	@Then("delete operation should fail as page template with id {string} should not be found")
	public void theServerShouldReturnAFailStatus(final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.withRepresentation(DEFAULT_REPRESENTATION)
				.hasStatusCode(SC_NOT_FOUND)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("No template with tmpl_id=%s found!", templateId));
		}
	}
}
