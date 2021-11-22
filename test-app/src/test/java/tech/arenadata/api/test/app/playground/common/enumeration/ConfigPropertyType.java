package tech.arenadata.api.test.app.playground.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Supported configuration properties enumeration.
 */
@Getter
@RequiredArgsConstructor
public enum ConfigPropertyType {
	/**
	 * Application host
	 */
	CONFIG_APP_HOST("config.app.host"),
	/**
	 * Application path
	 */
	CONFIG_APP_PATH("config.app.path"),
	/**
	 * Application port
	 */
	CONFIG_APP_PORT("config.app.port"),
	/**
	 * Resource bundle messages basename
	 */
	CONFIG_MESSAGES_BASENAME("config.messages.basename"),
	/**
	 * Client connect timeout
	 */
	CONFIG_CLIENT_CONNECT_TIMEOUT("config.client.connect.timeout"),
	/**
	 * Client connection request timeout
	 */
	CONFIG_CLIENT_CONNECTION_REQUEST_TIMEOUT("config.client.connection.request.timeout"),
	/**
	 * Client connection request timeout
	 */
	CONFIG_CLIENT_SOCKET_TIMEOUT("config.client.socket.timeout");

	/**
	 * Configuration property name
	 */
	private final String name;

	@Override
	public String toString() {
		return this.name;
	}
}
