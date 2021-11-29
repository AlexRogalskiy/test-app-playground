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
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static tech.arenadata.api.test.assertions.factory.Assertions.DEFAULT_REPRESENTATION;
import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_HTML;
import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_YAML;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.resourceToFile;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import tech.arenadata.api.test.assertions.factory.Assertions;
import tech.arenadata.api.test.commons.factory.ConfigurationFactory;

/** Page template upload definitions. */
public class PageStepUploadDefinitions extends BasePageStepDefinitions {

    private HttpEntityEnclosingRequestBase request;
    private MultipartEntityBuilder builder;
    private String url;

    @Given("^users want to upload page template$")
    public void usersPrepareRequestData() {
        this.url = this.getHttpClientConfigurer().createServerUrl();
        this.builder = MultipartEntityBuilder.create();
    }

    @And("users provide request header {string}:{string}")
    public void usersProvideRequestHeader(final String headerName, final String headerValue) {
        this.request.addHeader(headerName, headerValue);
    }

    @And("users provide page template file {string}")
    public void usersProvidePageTemplateFile(final String fileName) {
        final var basedir = ConfigurationFactory.getInstance().getTemplatesDir();
        this.builder.addPart(
                "file",
                new FileBody(resourceToFile(basedir, "request", fileName), APPLICATION_YAML));
    }

    @And("users provide page template id {string}")
    public void usersProvidePageTemplateId(final String templateId) {
        this.builder.addPart(
                "data",
                new StringBody(format("{\"tmpl_id\":\"%s\"}", templateId), APPLICATION_JSON));
    }

    @When("^users upload page template$")
    public void usersUploadPageTemplate() {
        this.request = new HttpPost(this.url);
        this.request.setEntity(this.builder.build());
    }

    @Then("page template with id {string} should be successfully uploaded")
    public void theServerShouldReturnASuccessStatus(final String templateId) throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(SC_CREATED)
                    .hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                    .hasFieldValue(
                            "message",
                            format("Template successfully uploaded. tmpl_id=%s", templateId));
        }
    }

    @Then("upload operation should fail with status \\({int})")
    public void theServerShouldReturnAFailStatus(final int status) throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(status)
                    .hasHeader(CONTENT_TYPE, APPLICATION_HTML.toString());
        }
    }

    @Then("upload operation should fail with status \\({int}) and message {string}")
    public void theServerShouldReturnAFailStatus(final int status, final String message)
            throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(status)
                    .hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                    .hasFieldValue("message", m -> m.startsWith(message));
        }
    }
}
