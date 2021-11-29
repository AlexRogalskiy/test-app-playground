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
package tech.arenadata.api.test.it.hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public class ScenarioInterceptorHooks {

    @BeforeAll
    public static void beforeScenarioAll() {
        final var capabilities = new DesiredCapabilities();
        capabilities.setCapability(
                "selenoid:options",
                Map.of(
                        "enableVNC", true,
                        "enableVideo", true));

        Configuration.browserCapabilities = capabilities;
    }

    @Before
    public void beforeScenario() {
        log.info(">>> Before running test scenario step >>>");
    }

    @After
    public void afterScenario() {
        log.info(">>> After running test scenario step >>>");
    }
}
