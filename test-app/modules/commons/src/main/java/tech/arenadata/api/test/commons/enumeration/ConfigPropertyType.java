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
package tech.arenadata.api.test.commons.enumeration;

import static tech.arenadata.api.test.commons.factory.ConfigurationConstants.CONFIG_PROPERTY_PREFIX;

import lombok.Getter;
import lombok.ToString;

/** Supported configuration properties enumeration. */
@Getter
@ToString
public enum ConfigPropertyType implements ConfigurationProperty {
    /** Server scheme */
    CONFIG_APP_SCHEME("app.scheme", "Configuration application schema"),
    /** Server host */
    CONFIG_APP_HOST("app.host", "Configuration application host"),
    /** Server path */
    CONFIG_APP_PATH("app.path", "Configuration application path"),
    /** Server port */
    CONFIG_APP_PORT("app.port", "Configuration application port"),
    /** Resource bundle messages basename */
    CONFIG_MESSAGES_BASENAME("messages.basename", "Configuration messages basename"),
    /** Client connect timeout */
    CONFIG_CLIENT_CONNECT_TIMEOUT("client.connect.timeout", "Configuration client connect timeout"),
    /** Client connection request timeout */
    CONFIG_CLIENT_CONNECTION_REQUEST_TIMEOUT(
            "client.connection.request.timeout", "Configuration client connection request timeout"),
    /** Client connection request timeout */
    CONFIG_CLIENT_SOCKET_TIMEOUT("client.socket.timeout", "Configuration client socket timeout"),
    /** Template base directory */
    CONFIG_TEMPLATES_BASEDIR("templates.basedir", "Configuration templates base directory");

    /** Configuration property name */
    @Getter(onMethod_ = {@Override})
    private final String key;
    /** Configuration property description */
    @Getter(onMethod_ = {@Override})
    private final String description;

    /**
     * Default property constructor by input {@link String} property key
     *
     * @param key initial input {@link String} key to operate by
     * @param description initial input {@link String} description to operate by
     */
    ConfigPropertyType(final String key, final String description) {
        this.key = CONFIG_PROPERTY_PREFIX + key;
        this.description = description;
    }
}
