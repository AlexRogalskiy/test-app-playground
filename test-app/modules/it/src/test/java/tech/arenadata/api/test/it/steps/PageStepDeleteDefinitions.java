package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import tech.arenadata.api.test.assertions.general.Assertions;

import java.io.IOException;

import static java.lang.String.format;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
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
		this.request.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
	}

	@Then("the page template {string} is deleted")
	public void theRequestedPageTemplateIsDeleted(final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(HttpStatus.SC_OK)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("Template with tmpl_id=%s successfully deleted!", templateId));
		}
	}

	@Then("delete operation should fail with status \\({int}) for page template {string}")
	public void theServerShouldReturnAFailStatus(final int status, final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("No template with tmpl_id=%s found!", templateId));
		}
	}
}
