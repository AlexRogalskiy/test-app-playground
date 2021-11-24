package tech.arenadata.api.test.commons.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import tech.arenadata.api.test.commons.enumeration.ConfigPropertyType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * General utilities for accessing configuration properties
 */
@Slf4j
@UtilityClass
public class ConfigurationUtils {

	private static final String CONFIGURATION_FILE = getConfigurationDir()
		+ "/config.properties";

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
		try {
			final var configFile = new File(CONFIGURATION_FILE);
			if (configFile.exists()) {
				properties.load(new FileInputStream(configFile));
			} else {
				throw new FileNotFoundException("Error: configuration File [" + CONFIGURATION_FILE + "] not found");
			}
		} catch (IOException ex) {
			log.error("Configuration could not be loaded.", ex);
			throw ex;
		}

		return properties;
	}

	private static String getConfigurationDir() {
		return Optional.ofNullable(System.getenv("CONFIG_DIR"))
			.orElse("src/test/resources");
	}
}
