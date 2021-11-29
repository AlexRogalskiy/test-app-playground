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
package tech.arenadata.api.test.it;

import java.security.Permission;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/** Test runner for cucumber test scenarios. */
@Slf4j
public final class TestRunner {

    /** Default cucumber configuration options */
    private static final String[] DEFAULT_OPTIONS = {
        "--glue", "tech.arenadata.api.test.it.hooks",
        "--glue", "tech.arenadata.api.test.it.steps",
        "--plugin", "pretty",
        "--plugin", "json:reports/cucumber/report.json",
        "--plugin", "html:reports/cucumber/report.html",
        "--tags", "@rest-api",
        "classpath:features"
    };

    public static void main(final String[] args) {
        final var manager = new IgnoreExitCall();
        System.setSecurityManager(manager);

        try {
            final var cucumberOptions =
                    Stream.concat(Stream.of(DEFAULT_OPTIONS), Stream.of(args))
                            .toArray(String[]::new);
            io.cucumber.core.cli.Main.run(cucumberOptions);
        } catch (SecurityException ex) {
            log.error("Processing security exception by ignoring exit", ex);
        }
    }

    /** Security manager patch with ignoring exit call exception */
    private static class IgnoreExitCall extends SecurityManager {

        @Override
        public void checkExit(final int status) {
            // request operations not permitted in current context
            // throw new SecurityException();
        }

        @Override
        public void checkPermission(final Permission permission) {
            // request operations not permitted in current context
            // super.checkPermission(permission);
        }
    }
}
