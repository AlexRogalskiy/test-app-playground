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
package tech.arenadata.api.test.commons.utils;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Supplier;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import tech.arenadata.api.test.commons.enumeration.ConfigPropertyType;

/** General utilities for accessing configuration properties */
@Slf4j
@UtilityClass
public class ConfigurationUtils {

    private static final String CONFIGURATION_FILE =
            Paths.get(getConfigurationDir(), "config.properties").toString();

    private static final Properties properties = getConfigurationProps();

    public static Supplier<String> getPropertySupplier(final ConfigPropertyType property) {
        return () -> getProperty(property);
    }

    public static String getProperty(final ConfigPropertyType property) {
        return getProperty(property.getKey());
    }

    public static String getProperty(final String property) {
        return properties.getProperty(property);
    }

    public static String getProperty(final String property, final String defaultValue) {
        return properties.getProperty(property, defaultValue);
    }

    @SneakyThrows
    private static Properties getConfigurationProps() {
        final var properties = new Properties();
        try (final var fileStream =
                ConfigurationUtils.class.getClassLoader().getResourceAsStream(CONFIGURATION_FILE)) {
            properties.load(fileStream);
        } catch (IOException ex) {
            log.error("Configuration could not be loaded.", ex);
            throw ex;
        }

        return properties;
    }

    private static String getConfigurationDir() {
        return ofNullable(System.getenv("CONFIG_DIR")).orElse(EMPTY);
    }
}
