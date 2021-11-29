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

import static java.lang.String.format;
import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static tech.arenadata.api.test.assertions.factory.Assertions.DEFAULT_REPRESENTATION;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import tech.arenadata.api.test.assertions.factory.Assertions;

/** Page template delete definitions. */
public class PageStepDeleteDefinitions extends BasePageStepDefinitions {

    private HttpRequestBase request;
    private String url;

    @Given("users want to delete uploaded page template with id {string}")
    public void usersPrepareUrl(final String templateId) {
        this.url = this.getHttpClientConfigurer().createServerUrl(templateId);
    }

    @When("^users delete information on the uploaded page template$")
    public void usersDeleteInformationOnPageTemplate() {
        this.request = new HttpDelete(this.url);
        this.request.addHeader(ACCEPT, APPLICATION_JSON.toString());
    }

    @Then("page template with id {string} should be successfully deleted")
    public void theServerShouldReturnASuccessStatus(final String templateId) throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(SC_OK)
                    .hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                    .hasFieldValue(
                            "message",
                            format("Template with tmpl_id=%s successfully deleted!", templateId));
        }
    }

    @Then("delete operation should fail as page template with id {string} should not be found")
    public void theServerShouldReturnAFailStatus(final String templateId) throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(SC_NOT_FOUND)
                    .hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                    .hasFieldValue(
                            "message", format("No template with tmpl_id=%s found!", templateId));
        }
    }
}
