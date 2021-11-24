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

	@Then("the server should return a success status \\(int) with the installed page template data {string}")
	public void theInstalledPageTemplateDataIsReturned(final int status, final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasContent(templateId);
		}
	}

	@Then("install operation should fail with status \\({int}) for page template {string}")
	public void theServerShouldReturnAFailStatus(final int status, final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("No template with tmpl_id=%s found!", templateId));
		}
	}
}
