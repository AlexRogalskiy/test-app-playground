package tech.arenadata.api.test.commons.interfaces;

import tech.arenadata.api.test.commons.factory.MessageFactory;

import java.util.Collection;

/**
 * Error template declaration with message code binding
 */
public interface ErrorTemplate {
	/**
	 * Returns {@link E} error template by input {@link String} message code
	 *
	 * @param messageCode - initial input {@link String} message code
	 * @return {@link E} error template
	 */
	static <E extends ErrorTemplate> E findByCode(
		final Collection<E> values, final String messageCode) {
		return values.stream()
			.filter(type -> type.getMessageCode().equalsIgnoreCase(messageCode))
			.findFirst()
			.orElse(null);
	}

	/**
	 * Returns {@link E} error template by input {@link String} message key
	 *
	 * @param messageKey - initial input {@link String} message key
	 * @return {@link E} error template
	 */
	static <E extends ErrorTemplate> E findByKey(
		final Collection<E> values, final String messageKey) {
		return values.stream()
			.filter(type -> type.getMessageKey().equalsIgnoreCase(messageKey))
			.findFirst()
			.orElse(null);
	}

	/**
	 * Return localized {@link String} message
	 *
	 * @param args initial input message arguments
	 * @return {@link String} error message
	 */
	default String getLocalizedMessage(final Object... args) {
		return MessageFactory.getInstance().getMessage(this.getMessageCode(), args);
	}

	/**
	 * Returns {@link String} template key
	 *
	 * @return {@link String} error message key
	 */
	String getMessageKey();

	/**
	 * Returns {@link String} template code
	 *
	 * @return {@link String} error message code
	 */
	String getMessageCode();
}
