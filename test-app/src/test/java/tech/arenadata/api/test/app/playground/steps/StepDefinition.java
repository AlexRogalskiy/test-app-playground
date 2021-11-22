//package tech.arenadata.api.test.app.playground.steps;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
//import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.containing;
//import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
//import static com.github.tomakehurst.wiremock.client.WireMock.get;
//import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.post;
//import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
//import static com.github.tomakehurst.wiremock.client.WireMock.verify;
//import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//
//public class StepDefinition {
//
//	private static final String CREATE_PATH = "/create";
//	private static final String APPLICATION_JSON = "application/json";
//
//	private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("cucumber.json");
//	private final String jsonString = new Scanner(jsonInputStream, StandardCharsets.UTF_8).useDelimiter("\\Z").next();
//
//	private final CloseableHttpClient httpClient = HttpClients.createDefault();
//
//	@When("^users upload data on a project$")
//	public void usersUploadDataOnAProject() throws IOException {
//		stubFor(post(urlEqualTo(CREATE_PATH))
//			.withHeader("content-type", equalTo(APPLICATION_JSON))
//			.withRequestBody(containing("testing-framework"))
//			.willReturn(aResponse().withStatus(200)));
//
//		final var request = new HttpPost("http://localhost:5000/create");
//		final var entity = new StringEntity(jsonString);
//		request.addHeader("content-type", APPLICATION_JSON);
//		request.setEntity(entity);
//		final var response = httpClient.execute(request);
//
//		assertEquals(200, response.getStatusLine().getStatusCode());
//		verify(postRequestedFor(urlEqualTo(CREATE_PATH))
//			.withHeader("content-type", equalTo(APPLICATION_JSON)));
//	}
//
//	//    @When("^users want to get information on the '(.+)' project$")
//	@When("users want to get information on the {string} project")
//	public void usersGetInformationOnAProject(String projectName) throws IOException {
//		stubFor(get(urlEqualTo("/projects/cucumber")).withHeader("accept", equalTo(APPLICATION_JSON))
//			.willReturn(aResponse().withBody(jsonString)));
//
//		final var request = new HttpGet("http://localhost:5000/projects/" + projectName.toLowerCase());
//		request.addHeader("accept", APPLICATION_JSON);
//		final var httpResponse = httpClient.execute(request);
//		final var responseString = convertResponseToString(httpResponse);
//
//		assertThat(responseString, containsString("\"testing-framework\": \"cucumber\""));
//		assertThat(responseString, containsString("\"website\": \"cucumber.io\""));
//		verify(getRequestedFor(urlEqualTo("/projects/cucumber")).withHeader("accept", equalTo(APPLICATION_JSON)));
//	}
//
//	@Then("the server should handle it and return a success status")
//	public void theServerShouldReturnASuccessStatus() {
//	}
//
//	@Then("the requested data is returned")
//	public void theRequestedDataIsReturned() {
//	}
//
//	private String convertResponseToString(HttpResponse response) throws IOException {
//		final var responseStream = response.getEntity().getContent();
//		final var scanner = new Scanner(responseStream, StandardCharsets.UTF_8);
//		final var responseString = scanner.useDelimiter("\\Z").next();
//		scanner.close();
//
//		return responseString;
//	}
//}
