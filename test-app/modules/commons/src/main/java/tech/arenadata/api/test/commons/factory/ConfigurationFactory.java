package tech.arenadata.api.test.commons.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tech.arenadata.api.test.commons.utils.ServiceUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static tech.arenadata.api.test.commons.enumeration.ConfigPropertyType.*;
import static tech.arenadata.api.test.commons.exception.ConfigurationException.*;
import static tech.arenadata.api.test.commons.utils.ConfigurationUtils.getProperty;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.*;

/**
 * Configuration factory that provides configuration properties
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigurationFactory {
	/**
	 * Default configuration property values
	 */
	private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.of(5_000, ChronoUnit.MILLIS);
	private static final Duration DEFAULT_CONNECTION_REQUEST_TIMEOUT = Duration.of(5_000, ChronoUnit.MILLIS);
	private static final Duration DEFAULT_SOCKET_TIMEOUT = Duration.of(5_000, ChronoUnit.MILLIS);

	/**
	 * Configuration factory default instance
	 */
	private static final ConfigurationFactory INSTANCE = new ConfigurationFactory();

	/**
	 * Returns default {@link ConfigurationFactory} instance.
	 *
	 * @return configuration factory
	 */
	public static ConfigurationFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns {@link String} server scheme.
	 *
	 * @return server scheme
	 */
	public String getServerScheme() {
		final var property = getProperty(CONFIG_APP_SCHEME);
		return getPropertyValueAsString(property)
			.orElseThrow(() -> createSchemeError(property));
	}

	/**
	 * Returns {@link String} server host.
	 *
	 * @return server host
	 */
	public String getServerHost() {
		final var property = getProperty(CONFIG_APP_HOST);
		return getPropertyValueAsString(property)
			.orElseThrow(() -> createHostError(property));
	}

	/**
	 * Returns {@link String} messages basename.
	 *
	 * @return messages basename
	 */
	public String getMessagesBasename() {
		final var property = getProperty(CONFIG_MESSAGES_BASENAME);
		return getPropertyValueAsString(property)
			.orElseThrow(() -> createMessagesBasenameError(property));
	}

	/**
	 * Returns {@code int} server port.
	 *
	 * @return server port
	 */
	public int getServerPort() {
		final var property = getProperty(CONFIG_APP_PORT);
		return getPropertyValueAsInt(property)
			.filter(ServiceUtils::isPositive)
			.orElseThrow(() -> createPortError(property));
	}

	/**
	 * Returns {@link String} server path.
	 *
	 * @return server path
	 */
	public String getServerPath() {
		final var property = getProperty(CONFIG_APP_PATH);
		return getPropertyValueAsString(property)
			.map(value -> !value.startsWith("/") ? "/" + value : value)
			.orElseThrow(() -> createPathError(property));
	}

	/**
	 * Returns {@link String} templates directory.
	 *
	 * @return templates directory
	 */
	public String getTemplatesDir() {
		final var property = getProperty(CONFIG_TEMPLATES_BASEDIR);
		return getPropertyValueAsString(property)
			.orElseThrow(() -> createTemplatesError(property));
	}

	/**
	 * Returns {@link Duration} connect timeout.
	 *
	 * @return connect timeout (in millis)
	 */
	public Duration getConnectTimeout() {
		final var property = getProperty(CONFIG_CLIENT_CONNECT_TIMEOUT);
		return getPropertyValueAsLong(property)
			.filter(ServiceUtils::isPositive)
			.map(Duration::ofMillis)
			.orElse(DEFAULT_CONNECT_TIMEOUT);
	}

	/**
	 * Returns {@link Duration} connection request timeout.
	 *
	 * @return connection request timeout (in millis)
	 */
	public Duration getConnectionRequestTimeout() {
		final var property = getProperty(CONFIG_CLIENT_CONNECTION_REQUEST_TIMEOUT);
		return getPropertyValueAsLong(property)
			.filter(ServiceUtils::isPositive)
			.map(Duration::ofMillis)
			.orElse(DEFAULT_CONNECTION_REQUEST_TIMEOUT);
	}

	/**
	 * Returns {@link Duration} socket timeout.
	 *
	 * @return socket timeout (in millis)
	 */
	public Duration getSocketTimeout() {
		final var property = getProperty(CONFIG_CLIENT_SOCKET_TIMEOUT);
		return getPropertyValueAsLong(property)
			.filter(ServiceUtils::isPositive)
			.map(Duration::ofMillis)
			.orElse(DEFAULT_SOCKET_TIMEOUT);
	}
}
