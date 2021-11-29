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
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static tech.arenadata.api.test.assertions.factory.Assertions.DEFAULT_REPRESENTATION;
import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_HTML;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import tech.arenadata.api.test.assertions.factory.Assertions;

/** Page template install definitions. */
public class PageStepInstallDefinitions extends BasePageStepDefinitions {

    private HttpEntityEnclosingRequestBase request;
    private String url;

    @Given("users want to install uploaded page template with id {string}")
    public void usersPrepareUrl(final String templateId) {
        this.url = this.getHttpClientConfigurer().createServerUrl(templateId, "install");
    }

    @When("^users install uploaded page template$")
    public void usersUploadPageTemplate() {
        this.request = new HttpPost(this.url);
    }

    @Then("page template with id {string} should be successfully installed")
    public void theServerShouldReturnASuccessStatus(final String templateId) throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(SC_OK)
                    .hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                    .hasFieldValue(
                            "message",
                            format("Template with tmpl_id=%s successfully installed!", templateId));
        }
    }

    @Then("install operation should fail with page template {string} not found")
    public void theServerShouldReturnANotFoundStatus(final String templateId) throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(SC_NOT_FOUND)
                    .hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                    .hasFieldValue(
                            "message", format("No template with tmpl_id=%s found!", templateId));
        }
    }

    @Then("install operation should fail with status \\({int}) and message {string}")
    public void theServerShouldReturnAFailStatus(final int status, final String message)
            throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(status)
                    .hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                    .hasFieldValue("message", message);
        }
    }

    @Then("install operation should fail with status \\({int})")
    public void theServerShouldReturnAFailStatus(final int status) throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(status)
                    .hasHeader(CONTENT_TYPE, APPLICATION_HTML.toString());
        }
    }
}
