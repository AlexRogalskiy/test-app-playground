package tech.arenadata.api.test.commons.factory;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class ConfigurationConstants {
	/**
	 * Default configuration property prefix
	 */
	public static final String CONFIG_PROPERTY_PREFIX = "config.";
	/**
	 * Default error template property prefix
	 */
	public static final String ERROR_TEMPLATE_PREFIX = "error.";

	/**
	 * Default connect timeout
	 */
	public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.of(5_000, ChronoUnit.MILLIS);
	/**
	 * Default connect request timeout
	 */
	public static final Duration DEFAULT_CONNECTION_REQUEST_TIMEOUT =
		Duration.of(5_000, ChronoUnit.MILLIS);
	/**
	 * Default socket timeout
	 */
	public static final Duration DEFAULT_SOCKET_TIMEOUT = Duration.of(5_000, ChronoUnit.MILLIS);
}
