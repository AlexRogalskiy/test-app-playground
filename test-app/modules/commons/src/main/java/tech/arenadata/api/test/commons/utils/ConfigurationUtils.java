package tech.arenadata.api.test.commons.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import tech.arenadata.api.test.commons.enumeration.ConfigPropertyType;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * General utilities for accessing configuration properties
 */
@Slf4j
@UtilityClass
public class ConfigurationUtils {

	private static final String CONFIGURATION_FILE = Paths.get(getConfigurationDir(), "config.properties").toString();

	private static final Properties properties = getConfigurationProps();

	public static String getProperty(final ConfigPropertyType property) {
		return getProperty(property.getName());
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
		try (final var fileStream = ConfigurationUtils.class.getClassLoader()
			.getResourceAsStream(CONFIGURATION_FILE)) {
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
