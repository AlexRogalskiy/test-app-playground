package tech.arenadata.api.test.it.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tech.arenadata.api.test.assertions.factory.Assertions;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.WebPageTemplate;
import tech.arenadata.api.test.commons.factory.ConfigurationFactory;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.resourceToString;

/**
 * Page template view definitions.
 */
public class PageStepViewDefinitions extends BasePageStepDefinitions {

	private ItemTemplate[] items;
	private WebPageTemplate page;

	@Given("users want to view installed template {string} on a web page")
	public void usersPrepareRequestData(final String fileName) throws IOException {
		final var basedir = ConfigurationFactory.getInstance().getTemplatesDir();
		final var data = resourceToString(basedir, "request", fileName);
		this.items = Assertions.assertThat(data)
			.isNotBlank()
			.asPOJO(ItemTemplate[].class);
	}

	@When("^users navigate to web page$")
	public void usersNavigateToUrl() {
		final var url = this.getHttpClientConfigurer().createBaseServerUrl();
		this.page = open(url, WebPageTemplate.class);
	}

	@Then("^page template should be correctly rendered$")
	public void theServerShouldReturnAValidPage() {
		Assertions.assertThat(this.page)
			.hasItems(this.items);

		closeWebDriver();
	}
}
