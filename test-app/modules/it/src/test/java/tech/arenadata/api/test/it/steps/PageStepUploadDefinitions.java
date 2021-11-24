package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import tech.arenadata.api.test.assertions.general.Assertions;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_HTML;
import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_YAML;

/**
 * Page template upload definitions
 */
public class PageStepUploadDefinitions extends BasePageStepDefinitions {

	private HttpEntityEnclosingRequestBase request;
	private String url;
	private String fileName;

	@Given("users want to upload page template {string}")
	public void usersNavigateToUrl(final String fileName) {
		this.url = this.getHttpClientConfigurer().createServerUrl();
		this.fileName = fileName;
	}

	@When("users upload page template")
	public void usersUploadPageTemplate() {
		this.request = new HttpPost(this.url);
		final var entity = MultipartEntityBuilder.create()
			.addPart("file", new FileBody(new File(this.fileName), APPLICATION_YAML))
			.build();
		this.request.setEntity(entity);
	}

	@When("users upload page template with header {string}:{string}")
	public void usersUploadPageTemplateWithHeader(final String headerName, final String headerValue) {
		this.request = new HttpPost(this.url);
		final var entity = MultipartEntityBuilder.create()
			.addPart("file", new FileBody(new File(this.fileName), APPLICATION_YAML))
			.build();
		this.request.addHeader(headerName, headerValue);
		this.request.setEntity(entity);
	}

	@When("users upload page template with id {string}")
	public void usersUploadPageTemplate(final String templateId) {
		final var url = this.getHttpClientConfigurer().createServerUrl();
		this.request = new HttpPost(url);
		final var entity = MultipartEntityBuilder.create()
			.addPart("file", new FileBody(new File(this.fileName), APPLICATION_YAML))
			.addPart("data", new StringBody(format("{\"tmpl_id\":\"%s\"}", templateId), APPLICATION_JSON))
			.build();
		//this.request.addHeader("Content-Type", "multipart/form-data");
		this.request.setEntity(entity);
	}

	@Then("upload operation should succeed with status \\({int}) for page template {string}")
	public void theServerShouldReturnASuccessStatus(final int status, final String templateId) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", format("Template successfully uploaded. tmpl_id=%s", templateId));
		}
	}

	@Then("upload operation should fail with status \\({int})")
	public void theServerShouldReturnAFailStatus(final int status) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_HTML.toString());
		}
	}

	@Then("upload operation should fail with status \\({int}) and message {string}")
	public void theServerShouldReturnAFailStatus(final int status, final String message) throws IOException {
		try (final var response = this.getHttpClient().execute(this.request)) {
			Assertions.assertThat(response)
				.hasStatusCode(status)
				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
				.hasFieldValue("message", message);
		}
	}
}
