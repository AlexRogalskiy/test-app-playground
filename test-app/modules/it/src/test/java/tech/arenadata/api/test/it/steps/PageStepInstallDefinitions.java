//package tech.arenadata.api.test.it.steps;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
//import org.apache.http.client.methods.HttpPost;
//import tech.arenadata.api.test.assertions.factory.Assertions;
//
//import java.io.IOException;
//
//import static java.lang.String.format;
//import static org.apache.http.HttpHeaders.CONTENT_TYPE;
//import static org.apache.http.HttpStatus.SC_NOT_FOUND;
//import static org.apache.http.HttpStatus.SC_OK;
//import static org.apache.http.entity.ContentType.APPLICATION_JSON;
//import static tech.arenadata.api.test.assertions.factory.Assertions.HTTP_REPRESENTATION;
//import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_HTML;
//
///**
// * Page template install definitions.
// */
//public class PageStepInstallDefinitions extends BasePageStepDefinitions {
//
//	private HttpEntityEnclosingRequestBase request;
//	private String url;
//
//	@Given("users want to install uploaded page template with id {string}")
//	public void usersPrepareRequestData(final String templateId) {
//		this.url = this.getHttpClientConfigurer().createServerUrl(templateId, "install");
//	}
//
//	@When("^users install uploaded page template$")
//	public void usersUploadPageTemplate() {
//		this.request = new HttpPost(this.url);
//	}
//
//	@Then("page template with id {string} should be successfully installed")
//	public void theServerShouldReturnASuccessStatus(final String templateId) throws IOException {
//		try (final var response = this.getHttpClient().execute(this.request)) {
//			Assertions.assertThat(response)
//				.withRepresentation(HTTP_REPRESENTATION)
//				.hasStatusCode(SC_OK)
//				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
//				.hasFieldValue("message", format("Template with tmpl_id=%s successfully installed!", templateId));
//		}
//	}
//
//	@Then("install operation should fail with page template {string} not found")
//	public void theServerShouldReturnANotFoundStatus(final String templateId) throws IOException {
//		try (final var response = this.getHttpClient().execute(this.request)) {
//			Assertions.assertThat(response)
//				.withRepresentation(HTTP_REPRESENTATION)
//				.hasStatusCode(SC_NOT_FOUND)
//				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
//				.hasFieldValue("message", format("No template with tmpl_id=%s found!", templateId));
//		}
//	}
//
//	@Then("install operation should fail with status \\({int}) and message {string}")
//	public void theServerShouldReturnAFailStatus(final int status, final String message) throws IOException {
//		try (final var response = this.getHttpClient().execute(this.request)) {
//			Assertions.assertThat(response)
//				.withRepresentation(HTTP_REPRESENTATION)
//				.hasStatusCode(status)
//				.hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
//				.hasFieldValue("message", message);
//		}
//	}
//
//	@Then("install operation should fail with status \\({int})")
//	public void theServerShouldReturnAFailStatus(final int status) throws IOException {
//		try (final var response = this.getHttpClient().execute(this.request)) {
//			Assertions.assertThat(response)
//				.withRepresentation(HTTP_REPRESENTATION)
//				.hasStatusCode(status)
//				.hasHeader(CONTENT_TYPE, APPLICATION_HTML.toString());
//		}
//	}
//}
