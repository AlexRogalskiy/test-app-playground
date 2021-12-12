/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2021-present Alexander Rogalskiy
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package tech.arenadata.api.test.commons.enumeration;

import lombok.Getter;

import static tech.arenadata.api.test.commons.factory.ConfigurationConstants.CONFIG_PROPERTY_PREFIX;

/**
 * Supported configuration properties enumeration.
 */
@Getter
public enum ConfigPropertyType implements ConfigurationProperty {
	/**
	 * Server scheme
	 */
	CONFIG_APP_SCHEME("app.scheme"),
	/**
	 * Server host
	 */
	CONFIG_APP_HOST("app.host"),
	/**
	 * Server path
	 */
	CONFIG_APP_PATH("app.path"),
	/**
	 * Server port
	 */
	CONFIG_APP_PORT("app.port"),
	/**
	 * Resource bundle messages basename
	 */
	CONFIG_MESSAGES_BASENAME("messages.basename"),
	/**
	 * Client connect timeout
	 */
	CONFIG_CLIENT_CONNECT_TIMEOUT("client.connect.timeout"),
	/**
	 * Client connection request timeout
	 */
	CONFIG_CLIENT_CONNECTION_REQUEST_TIMEOUT("client.connection.request.timeout"),
	/**
	 * Client connection request timeout
	 */
	CONFIG_CLIENT_SOCKET_TIMEOUT("client.socket.timeout"),
	/**
	 * Template base directory
	 */
	CONFIG_TEMPLATES_BASEDIR("templates.basedir");

	/**
	 * Configuration property name
	 */
	private final String key;

	/**
	 * Default property constructor by input {@link String} property key
	 *
	 * @param key initial input {@link String} key to operate by
	 */
	ConfigPropertyType(final String key) {
		this.key = CONFIG_PROPERTY_PREFIX + key;
	}

	@Override
	public String toString() {
		return this.key;
	}
}
