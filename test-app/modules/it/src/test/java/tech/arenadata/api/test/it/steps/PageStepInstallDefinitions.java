package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import tech.arenadata.api.test.assertions.general.Assertions;

import java.io.IOException;

import static java.lang.String.format;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * Page template install definitions
 */
public class PageStepInstallDefinitions extends BasePageStepDefinitions {

	private HttpEntityEnclosingRequestBase request;
	private String url;

	@Given("users want to install uploaded page template {string}")
	public void usersNavigateToUrl(final String templateId) {
		this.url = this.getHttpClientConfigurer().createServerUrl(templateId, "install");
	}

	@When("^users install uploaded page template$")
	public void usersUploadPageTemplate() {
		this.request = new HttpPost(this.url);
	}

	@Then("install operation should succeed with status \\({int}) for page template {string}")
	public void theInstalledPageTemplateDataIsReturned(final int status, final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("Template with tmpl_id=%s successfully installed!", templateId));
		}
	}

	@Then("install operation should fail with page template {string} not found")
	public void theServerShouldReturnANotFoundStatus(final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(SC_NOT_FOUND)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("No template with tmpl_id=%s found!", templateId));
		}
	}

	@Then("install operation should fail with status \\({int}) and message {string}")
	public void theServerShouldReturnAFailStatus(final int status, final String message) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", message);
		}
	}
}
