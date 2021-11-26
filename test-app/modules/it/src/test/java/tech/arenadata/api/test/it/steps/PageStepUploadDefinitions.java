//package tech.arenadata.api.test.it.steps;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import tech.arenadata.api.test.assertions.factory.Assertions;
//import tech.arenadata.api.test.assertions.core.CustomRepresentation;
//
//import java.io.File;
//import java.io.IOException;
//
//import static java.lang.String.format;
//import static org.apache.http.HttpHeaders.CONTENT_TYPE;
//import static org.apache.http.HttpStatus.SC_CREATED;
//import static org.apache.http.entity.ContentType.APPLICATION_JSON;
//import static tech.arenadata.api.test.assertions.factory.Assertions.HTTP_REPRESENTATION;
//import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_HTML;
//import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_YAML;
//
///**
// * Page template upload definitions.
// */
//public class PageStepUploadDefinitions extends BasePageStepDefinitions {
//
//	private HttpEntityEnclosingRequestBase request;
//	private MultipartEntityBuilder builder;
//	private String url;
//
//	@Given("^users want to upload page template$")
//	public void usersPrepareRequestData() {
//		this.url = this.getHttpClientConfigurer().createServerUrl();
//		this.builder = MultipartEntityBuilder.create();
//	}
//
//	@And("users provide request header {string}:{string}")
//	public void usersProvideRequestHeader(final String headerName, final String headerValue) {
//		this.request.addHeader(headerName, headerValue);
//	}
//
//	@And("users provide page template file {string}")
//	public void usersProvidePageTemplateFile(final String fileName) {
//		this.builder.addPart("file", new FileBody(new File(fileName), APPLICATION_YAML));
//	}
//
//	@And("users provide page template id {string}")
//	public void usersProvidePageTemplateId(final String templateId) {
//		this.builder.addPart("data", new StringBody(format("{\"tmpl_id\":\"%s\"}", templateId), APPLICATION_JSON));
//	}
//
//	@When("^users upload page template$")
//	public void usersUploadPageTemplate() {
//		this.request = new HttpPost(this.url);
//		this.request.setEntity(this.builder.build());
//	}
//
//	@Then("page template with id {string} should be successfully uploaded")
//	public void theServerShouldReturnASuccessStatus(final String templateId) throws IOException {
//		try (final var response = this.getHttpClient().execute(this.request)) {
//			Assertions.assertThat(response)
//				.withRepresentation(HTTP_REPRESENTATION)
//				.hasStatusCode(SC_CREATED)
//				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
//				.hasFieldValue("message", format("Template successfully uploaded. tmpl_id=%s", templateId));
//		}
//	}
//
//	@Then("upload operation should fail with status \\({int})")
//	public void theServerShouldReturnAFailStatus(final int status) throws IOException {
//		try (final var response = this.getHttpClient().execute(this.request)) {
//			Assertions.assertThat(response)
//				.withRepresentation(HTTP_REPRESENTATION)
//				.withRepresentation(new CustomRepresentation())
//				.hasStatusCode(status)
//				.hasHeader(CONTENT_TYPE, APPLICATION_HTML.toString());
//		}
//	}
//
//	@Then("upload operation should fail with status \\({int}) and message {string}")
//	public void theServerShouldReturnAFailStatus(final int status, final String message) throws IOException {
//		try (final var response = this.getHttpClient().execute(this.request)) {
//			Assertions.assertThat(response)
//				.withRepresentation(HTTP_REPRESENTATION)
//				.hasStatusCode(status)
//				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
//				.hasFieldValue("message", message);
//		}
//	}
//}
