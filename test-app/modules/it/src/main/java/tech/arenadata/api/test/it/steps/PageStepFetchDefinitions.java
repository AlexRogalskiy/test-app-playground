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

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static tech.arenadata.api.test.assertions.factory.Assertions.DEFAULT_REPRESENTATION;
import static tech.arenadata.api.test.client.utils.HttpClientUtils.APPLICATION_HTML;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.compareArrays;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.resourceToString;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import tech.arenadata.api.test.assertions.factory.Assertions;
import tech.arenadata.api.test.assertions.model.ResponseData;
import tech.arenadata.api.test.commons.factory.ConfigurationFactory;

/** Page template fetch definitions. */
public class PageStepFetchDefinitions extends BasePageStepDefinitions {

    private HttpRequestBase request;
    private String url;

    @Given("^users want to fetch uploaded page templates$")
    public void usersPrepareUrl() {
        this.url = this.getHttpClientConfigurer().createServerUrl();
    }

    @When("^users request information on the uploaded page templates$")
    public void usersRequestInformationOnPageTemplates() {
        this.request = new HttpGet(this.url);
        this.request.addHeader(ACCEPT, APPLICATION_JSON.toString());
    }

    @Then("page templates data {string} should be successfully returned")
    public void theServerShouldReturnASuccessStatus(final String fileName) throws IOException {
        final var basedir = ConfigurationFactory.getInstance().getTemplatesDir();
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(SC_OK)
                    .hasHeader(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                    .hasMatch(
                            resourceToString(basedir, "response", fileName),
                            ResponseData.class,
                            (v1, v2) -> compareArrays(v1.getTemplates(), v2.getTemplates()));
        }
    }

    @Then("fetch operation should fail with status \\({int})")
    public void theServerShouldReturnAFailStatus(final int status) throws IOException {
        try (final var response = this.getHttpClient().execute(this.request)) {
            Assertions.assertThat(response)
                    .withRepresentation(DEFAULT_REPRESENTATION)
                    .hasStatusCode(status)
                    .hasHeader(CONTENT_TYPE, APPLICATION_HTML.toString());
        }
    }
}
