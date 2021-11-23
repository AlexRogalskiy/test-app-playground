package tech.arenadata.api.test.commons.exception;

import lombok.NonNull;

import static tech.arenadata.api.test.commons.enumeration.GeneralErrorTemplateType.*;

/**
 * Configuration {@link RuntimeException} implementation
 */
public class ConfigurationException extends RuntimeException {
	/**
	 * Default explicit serialVersionUID for interoperability
	 */
	private static final long serialVersionUID = -2297995868660672241L;

	/**
	 * {@link ConfigurationException} constructor with initial input message
	 *
	 * @param message - initial input message {@link String}
	 */
	public ConfigurationException(final String message) {
		super(message);
	}

	/**
	 * {@link ConfigurationException} constructor with initial input {@link Throwable}
	 *
	 * @param cause - initial input cause target {@link Throwable}
	 */
	public ConfigurationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * {@link ConfigurationException} constructor with initial input message and {@link
	 * Throwable}
	 *
	 * @param message - initial input message {@link String}
	 * @param cause   - initial input cause target {@link Throwable}
	 */
	public ConfigurationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Returns {@link ConfigurationException} by input parameters
	 *
	 * @param message - initial input description {@link String}
	 * @return {@link ConfigurationException}
	 */
	@NonNull
	public static ConfigurationException createError(@NonNull final String message) {
		return new ConfigurationException(message);
	}

	/**
	 * Returns host {@link ConfigurationException} by input parameters
	 *
	 * @param args - initial input message arguments {@link Object}
	 * @return host {@link ConfigurationException}
	 */
	@NonNull
	public static ConfigurationException createHostError(final Object... args) {
		return createError(CONFIGURATION_HOST_ERROR.getLocalizedMessage(args));
	}

	/**
	 * Returns port {@link ConfigurationException} by input parameters
	 *
	 * @param args - initial input message arguments {@link Object}
	 * @return port {@link ConfigurationException}
	 */
	@NonNull
	public static ConfigurationException createPortError(final Object... args) {
		return createError(CONFIGURATION_PORT_ERROR.getLocalizedMessage(args));
	}

	/**
	 * Returns path {@link ConfigurationException} by input parameters
	 *
	 * @param args - initial input message arguments {@link Object}
	 * @return path {@link ConfigurationException}
	 */
	@NonNull
	public static ConfigurationException createPathError(final Object... args) {
		return createError(CONFIGURATION_PATH_ERROR.getLocalizedMessage(args));
	}

	/**
	 * Returns messages basename {@link ConfigurationException} by input parameters
	 *
	 * @param args - initial input message arguments {@link Object}
	 * @return port {@link ConfigurationException}
	 */
	@NonNull
	public static ConfigurationException createMessagesBasenameError(final Object... args) {
		return createError(CONFIGURATION_MESSAGES_BASENAME_ERROR.getLocalizedMessage(args));
	}
}
