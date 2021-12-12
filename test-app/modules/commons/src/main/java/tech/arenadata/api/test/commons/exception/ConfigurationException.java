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
package tech.arenadata.api.test.commons.exception;

import lombok.NonNull;
import tech.arenadata.api.test.commons.interfaces.ErrorTemplate;

import java.util.function.Supplier;

import static tech.arenadata.api.test.commons.enumeration.GeneralErrorTemplateType.*;

/**
 * Configuration {@link LocalizableException} implementation
 */
public class ConfigurationException extends LocalizableException {
	/**
	 * Default explicit serialVersionUID for interoperability
	 */
	private static final long serialVersionUID = -2297995868660672241L;

	/**
	 * {@link ConfigurationException} constructor with initial input message
	 *
	 * @param template initial input {@link ErrorTemplate} message
	 * @param args     initial input message {@link Object} collection of message arguments
	 */
	public ConfigurationException(final ErrorTemplate template, final Object... args) {
		super(template, args);
	}

	/**
	 * Returns {@link ConfigurationException} by input parameters
	 *
	 * @param template initial input {@link ErrorTemplate} message
	 * @param args     initial input message {@link Object} collection of message arguments
	 * @return {@link ConfigurationException}
	 */
	@NonNull
	public static Supplier<ConfigurationException> createError(final ErrorTemplate template, final Object... args) {
		return () -> new ConfigurationException(template, args);
	}

	/**
	 * Returns scheme {@link ConfigurationException} by input parameters
	 *
	 * @param args initial input message arguments {@link Object}
	 * @return host {@link ConfigurationException}
	 */
	@NonNull
	public static Supplier<ConfigurationException> createSchemeError(final Object... args) {
		return createError(CONFIGURATION_SCHEME_ERROR, args);
	}

	/**
	 * Returns host {@link ConfigurationException} by input parameters
	 *
	 * @param args initial input message arguments {@link Object}
	 * @return host {@link ConfigurationException}
	 */
	@NonNull
	public static Supplier<ConfigurationException> createHostError(final Object... args) {
		return createError(CONFIGURATION_HOST_ERROR, args);
	}

	/**
	 * Returns port {@link ConfigurationException} by input parameters
	 *
	 * @param args initial input message arguments {@link Object}
	 * @return port {@link ConfigurationException}
	 */
	@NonNull
	public static Supplier<ConfigurationException> createPortError(final Object... args) {
		return createError(CONFIGURATION_PORT_ERROR, args);
	}

	/**
	 * Returns path {@link ConfigurationException} by input parameters
	 *
	 * @param args initial input message arguments {@link Object}
	 * @return path {@link ConfigurationException}
	 */
	@NonNull
	public static Supplier<ConfigurationException> createPathError(final Object... args) {
		return createError(CONFIGURATION_PATH_ERROR, args);
	}

	/**
	 * Returns templates {@link ConfigurationException} by input parameters
	 *
	 * @param args initial input message arguments {@link Object}
	 * @return path {@link ConfigurationException}
	 */
	@NonNull
	public static Supplier<ConfigurationException> createTemplatesError(final Object... args) {
		return createError(CONFIGURATION_TEMPLATES_DIR_ERROR, args);
	}

	/**
	 * Returns messages basename {@link ConfigurationException} by input parameters
	 *
	 * @param args initial input message arguments {@link Object}
	 * @return port {@link ConfigurationException}
	 */
	@NonNull
	public static Supplier<ConfigurationException> createMessagesBasenameError(final Object... args) {
		return createError(CONFIGURATION_MESSAGES_BASENAME_ERROR, args);
	}
}
