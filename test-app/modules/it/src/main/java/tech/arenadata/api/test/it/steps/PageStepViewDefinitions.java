/**
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2021-present Alexander Rogalskiy
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package tech.arenadata.api.test.it.steps;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.resourceToString;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tech.arenadata.api.test.assertions.factory.Assertions;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.WebPageTemplate;
import tech.arenadata.api.test.commons.factory.ConfigurationFactory;

/** Page template view definitions. */
public class PageStepViewDefinitions extends BasePageStepDefinitions {

    private ItemTemplate[] items;
    private WebPageTemplate page;
    private String url;

    @Given("^users want to view installed page template on a web page$")
    public void usersPrepareUrl() {
        this.url = this.getHttpClientConfigurer().createBaseServerUrl();
    }

    @And("users want to compare with file template {string}")
    public void usersPrepareRequestData(final String fileName) {
        final var basedir = ConfigurationFactory.getInstance().getTemplatesDir();
        final var data = resourceToString(basedir, "request", fileName);
        this.items = Assertions.assertThat(data).isNotBlank().asPOJO(ItemTemplate[].class);
    }

    @When("^users navigate to web page$")
    public void usersNavigateToUrl() {
        this.page = open(this.url, WebPageTemplate.class);
    }

    @Then("^page template should be correctly rendered$")
    public void theServerShouldReturnAValidPage() {
        Assertions.assertThat(this.page).hasItems(this.items, ".container", ".btn");

        closeWebDriver();
    }

    @Then("^web page should be empty$")
    public void theServerShouldReturnAnEmptyPage() {
        Assertions.assertThat(this.page)
                .hasNoElements(".container", ".btn")
                .hasElement("h3", "No template uploaded or your template is empty...");

        closeWebDriver();
    }
}
