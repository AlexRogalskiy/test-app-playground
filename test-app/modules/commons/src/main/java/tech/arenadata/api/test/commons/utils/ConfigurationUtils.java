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
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import tech.arenadata.api.test.commons.enumeration.ConfigPropertyType;

/** General utilities for accessing configuration properties */
@Slf4j
@UtilityClass
public class ConfigurationUtils {

    private static final String CONFIGURATION_FILE =
            Paths.get(getConfigurationDir(), "config.properties").toString();

    private static final Properties properties = getGeneralProperties();

    public static Supplier<String> getPropertySupplier(final ConfigPropertyType property) {
        return () -> getProperty(property);
    }

    public static String getProperty(final ConfigPropertyType property) {
        return getProperty(property.getKey());
    }

    public static String getProperty(final String property) {
        return properties.getProperty(property);
    }

    public static String get(final String key, final String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public boolean contains(final String key) {
        return properties.containsKey(key);
    }

    public static String getProperty(final String property, final String defaultValue) {
        return properties.getProperty(property, defaultValue);
    }

    public static Map<String, Object> getPropertyMap() {
        return properties.keySet().stream()
                .collect(
                        collectingAndThen(
                                toMap(Object::toString, properties::get),
                                Collections::unmodifiableMap));
    }

    public static Map<String, String> getPropertyMap(final String prefix) {
        final var prefixLength = prefix.length();
        return properties.stringPropertyNames().stream()
                .filter(key -> StringUtils.startsWithIgnoreCase(key, prefix))
                .collect(
                        collectingAndThen(
                                toMap(key -> key.substring(prefixLength), properties::getProperty),
                                Collections::unmodifiableMap));
    }

    public static Map<String, Object> getPropertiesByKey(final String key) {
        return properties.entrySet().stream()
                .filter(e -> e.getKey().toString().startsWith(key))
                .collect(
                        collectingAndThen(
                                toMap(
                                        e -> e.getKey().toString().replace(key.concat("."), EMPTY),
                                        Map.Entry::getValue),
                                Collections::unmodifiableMap));
    }

    public static Properties getGeneralProperties() {
        final var properties = new Properties();
        final var systemProperties = getSystemProperties();
        systemProperties.keySet().forEach(k -> properties.put(k, systemProperties.get(k)));

        final var configProperties = getConfigurationProperties();
        configProperties.keySet().forEach(k -> properties.put(k, configProperties.get(k)));

        return properties;
    }

    private static Properties getSystemProperties() {
        try {
            return System.getProperties();
        } catch (Exception ex) {
            log.error("System properties could not be loaded.", ex);
            throw ex;
        }
    }

    private static Properties getConfigurationProperties() {
        final var properties = new Properties();
        loadProperty(properties, CONFIGURATION_FILE);

        return properties;
    }

    public static Properties loadProperty(final String... filePaths) {
        final var properties = new Properties();
        for (final var path : filePaths) {
            loadProperty(properties, path);
        }

        return properties;
    }

	public static Properties loadProperty(final String string, final String delim) {
		final var properties = new Properties();
		final var formattedString = string.trim().replace(delim, System.lineSeparator());
		loadString(properties, formattedString);

		return properties;
	}

	@SneakyThrows
    private static void loadProperty(final Properties properties, final String filePath) {
        try (final var fileReader =
                ConfigurationUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            properties.load(fileReader);
        } catch (IOException ex) {
            log.error("Configuration properties could not be loaded.", ex);
            throw ex;
        }
    }

	@SneakyThrows
	private static void loadString(final Properties properties, final String formattedString) {
		try (final var stringReader = new StringReader(formattedString)) {
			properties.load(stringReader);
		} catch (IOException ex) {
			log.error("Configuration properties could not be loaded.", ex);
			throw ex;
		}
	}

    private static String getConfigurationDir() {
        return ofNullable(System.getenv("CONFIG_DIR")).orElse(EMPTY);
    }
}
