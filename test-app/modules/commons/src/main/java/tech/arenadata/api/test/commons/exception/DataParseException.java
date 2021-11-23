package tech.arenadata.api.test.commons.exception;

import lombok.NonNull;

import static tech.arenadata.api.test.commons.enumeration.GeneralErrorTemplateType.DATA_JSON_PARSE_ERROR;

/**
 * Data parse {@link RuntimeException} implementation
 */
public class DataParseException extends RuntimeException {
	/**
	 * Default explicit serialVersionUID for interoperability
	 */
	private static final long serialVersionUID = -8863630294671317139L;

	/**
	 * {@link DataParseException} constructor with initial input message
	 *
	 * @param message - initial input message {@link String}
	 */
	public DataParseException(final String message) {
		super(message);
	}

	/**
	 * {@link DataParseException} constructor with initial input {@link Throwable}
	 *
	 * @param cause - initial input cause target {@link Throwable}
	 */
	public DataParseException(final Throwable cause) {
		super(cause);
	}

	/**
	 * {@link DataParseException} constructor with initial input message and {@link
	 * Throwable}
	 *
	 * @param message - initial input message {@link String}
	 * @param cause   - initial input cause target {@link Throwable}
	 */
	public DataParseException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Returns {@link DataParseException} by input parameters
	 *
	 * @param message - initial input description {@link String}
	 * @return {@link DataParseException}
	 */
	@NonNull
	public static DataParseException createError(@NonNull final String message) {
		return new DataParseException(message);
	}

	/**
	 * Returns json {@link DataParseException} by input parameters
	 *
	 * @param args - initial input message arguments {@link Object}
	 * @return host {@link DataParseException}
	 */
	@NonNull
	public static DataParseException createJsonParseError(final Object... args) {
		return createError(DATA_JSON_PARSE_ERROR.getLocalizedMessage(args));
	}
}
