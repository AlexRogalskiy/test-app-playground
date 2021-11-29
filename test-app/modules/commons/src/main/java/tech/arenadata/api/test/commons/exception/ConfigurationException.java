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
package tech.arenadata.api.test.commons.exception;

import static tech.arenadata.api.test.commons.enumeration.GeneralErrorTemplateType.*;

import lombok.NonNull;

/** Configuration {@link RuntimeException} implementation */
public class ConfigurationException extends RuntimeException {
    /** Default explicit serialVersionUID for interoperability */
    private static final long serialVersionUID = -2297995868660672241L;

    /**
     * {@link ConfigurationException} constructor with initial input message
     *
     * @param message initial input message {@link String}
     */
    public ConfigurationException(final String message) {
        super(message);
    }

    /**
     * {@link ConfigurationException} constructor with initial input {@link Throwable}
     *
     * @param cause initial input cause target {@link Throwable}
     */
    public ConfigurationException(final Throwable cause) {
        super(cause);
    }

    /**
     * {@link ConfigurationException} constructor with initial input message and {@link Throwable}
     *
     * @param message initial input message {@link String}
     * @param cause initial input cause target {@link Throwable}
     */
    public ConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link ConfigurationException} by input parameters
     *
     * @param message initial input description {@link String}
     * @return {@link ConfigurationException}
     */
    @NonNull
    public static ConfigurationException createError(@NonNull final String message) {
        return new ConfigurationException(message);
    }

    /**
     * Returns scheme {@link ConfigurationException} by input parameters
     *
     * @param args initial input message arguments {@link Object}
     * @return host {@link ConfigurationException}
     */
    @NonNull
    public static ConfigurationException createSchemeError(final Object... args) {
        return createError(CONFIGURATION_SCHEME_ERROR.getLocalizedMessage(args));
    }

    /**
     * Returns host {@link ConfigurationException} by input parameters
     *
     * @param args initial input message arguments {@link Object}
     * @return host {@link ConfigurationException}
     */
    @NonNull
    public static ConfigurationException createHostError(final Object... args) {
        return createError(CONFIGURATION_HOST_ERROR.getLocalizedMessage(args));
    }

    /**
     * Returns port {@link ConfigurationException} by input parameters
     *
     * @param args initial input message arguments {@link Object}
     * @return port {@link ConfigurationException}
     */
    @NonNull
    public static ConfigurationException createPortError(final Object... args) {
        return createError(CONFIGURATION_PORT_ERROR.getLocalizedMessage(args));
    }

    /**
     * Returns path {@link ConfigurationException} by input parameters
     *
     * @param args initial input message arguments {@link Object}
     * @return path {@link ConfigurationException}
     */
    @NonNull
    public static ConfigurationException createPathError(final Object... args) {
        return createError(CONFIGURATION_PATH_ERROR.getLocalizedMessage(args));
    }

    /**
     * Returns templates {@link ConfigurationException} by input parameters
     *
     * @param args initial input message arguments {@link Object}
     * @return path {@link ConfigurationException}
     */
    @NonNull
    public static ConfigurationException createTemplatesError(final Object... args) {
        return createError(CONFIGURATION_TEMPLATES_DIR_ERROR.getLocalizedMessage(args));
    }

    /**
     * Returns messages basename {@link ConfigurationException} by input parameters
     *
     * @param args initial input message arguments {@link Object}
     * @return port {@link ConfigurationException}
     */
    @NonNull
    public static ConfigurationException createMessagesBasenameError(final Object... args) {
        return createError(CONFIGURATION_MESSAGES_BASENAME_ERROR.getLocalizedMessage(args));
    }
}
