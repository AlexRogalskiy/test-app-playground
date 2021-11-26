//package tech.arenadata.api.test.it.steps;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import tech.arenadata.api.test.assertions.factory.Assertions;
//import tech.arenadata.api.test.assertions.model.ItemTemplate;
//import tech.arenadata.api.test.assertions.model.WebPageTemplate;
//
//import java.io.File;
//import java.io.IOException;
//
//import static com.codeborne.selenide.Selenide.closeWebDriver;
//import static com.codeborne.selenide.Selenide.open;
//import static java.nio.charset.StandardCharsets.UTF_8;
//import static org.apache.commons.io.FileUtils.readFileToString;
//
///**
// * Page template view definitions.
// */
//public class PageStepViewDefinitions extends BasePageStepDefinitions {
//
//	private ItemTemplate[] items;
//	private WebPageTemplate page;
//
//	@Given("users want to view installed template {string} on a web page")
//	public void usersPrepareRequestData(final String content) throws IOException {
//		final var data = readFileToString(new File(content), UTF_8);
//		this.items = Assertions.assertThat(data)
//			.isNotBlank()
//			.asPOJO(ItemTemplate[].class);
//	}
//
//	@When("^users navigate to web page$")
//	public void usersNavigateToUrl() {
//		final var url = this.getHttpClientConfigurer().createBaseServerUrl();
//		this.page = open(url, WebPageTemplate.class);
//	}
//
//	@Then("^page template should be correctly rendered$")
//	public void theServerShouldReturnAValidPage() {
//		Assertions.assertThat(this.page)
//			.hasItems(this.items);
//
//		closeWebDriver();
//	}
//}
