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
package tech.arenadata.api.test.commons.factory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.experimental.UtilityClass;

/** Configuration property constants */
@UtilityClass
public class ConfigurationConstants {
    /** Default configuration property prefix */
    public static final String PROPERTY_DELIMITER = ".";
    //    /** Default configuration property prefix */
    //    public static final String CONFIG_PROPERTY_PREFIX = "config" + PROPERTY_DELIMITER;
    //    /** Default error template property prefix */
    //    public static final String ERROR_TEMPLATE_PREFIX = "error" + PROPERTY_DELIMITER;

    /** Default connect timeout */
    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.of(5_000, ChronoUnit.MILLIS);
    /** Default connect request timeout */
    public static final Duration DEFAULT_CONNECTION_REQUEST_TIMEOUT =
            Duration.of(5_000, ChronoUnit.MILLIS);
    /** Default socket timeout */
    public static final Duration DEFAULT_SOCKET_TIMEOUT = Duration.of(5_000, ChronoUnit.MILLIS);
}
