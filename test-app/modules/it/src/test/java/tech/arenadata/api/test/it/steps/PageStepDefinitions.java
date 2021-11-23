package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageStepDefinitions {

	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	@When("^users upload data on a project$")
	public void usersUploadPageTemplate() throws IOException {
		final var request = new HttpPost("http://localhost:15000/api/v1/templates");
		final var entity = new StringEntity("test", UTF_8);
		request.addHeader("content-type", "application/yml");
		request.setEntity(entity);

		final var response = httpClient.execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());
	}

	//    @When("^users want to get information on the '(.+)' project$")
	@When("users want to get information on the {string} project")
	public void usersGetInformationOnPage(final String projectName) throws IOException {
		final var request = new HttpGet("http://localhost:15000/api/v1/templates");
		request.addHeader("accept", "application/json");
		final var httpResponse = this.httpClient.execute(request);
		final var responseString = convertResponseToString(httpResponse);

		assertThat(responseString, containsString("\"testing-framework\": \"cucumber\""));
		assertThat(responseString, containsString("\"website\": \"cucumber.io\""));
	}

	@Then("the server should handle it and return a success status")
	public void theServerShouldReturnASuccessStatus() {
	}

	@Then("the requested data is returned")
	public void theRequestedDataIsReturned() {
	}

	private String convertResponseToString(final HttpResponse response) throws IOException {
		final var responseStream = response.getEntity().getContent();
		final var scanner = new Scanner(responseStream, StandardCharsets.UTF_8);
		final var responseString = scanner.useDelimiter("\\Z").next();
		scanner.close();

		return responseString;
	}
}
