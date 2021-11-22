package tech.arenadata.api.test.app.playground.common.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Message factory that provides localized messages by resource bundles
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageFactory {
	/**
	 * {@link Locale} local instance
	 */
	private static final ThreadLocal<Locale> LOCALE = ThreadLocal.withInitial(Locale::getDefault);
	/**
	 * {@link MessageFormat} local instance
	 */
	private static final ThreadLocal<MessageFormat> MESSAGE_FORMAT = ThreadLocal.withInitial(() -> new MessageFormat(EMPTY));

	/**
	 * Message factory default instance
	 */
	private static final MessageFactory INSTANCE = new MessageFactory();

	/**
	 * Returns default {@link MessageFactory} instance
	 *
	 * @return message factory
	 */
	public static MessageFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns {@link ResourceBundle} by input locale
	 *
	 * @param locale initial input {@link Locale} locale to operate by
	 * @return localized resource bundle
	 */
	public static ResourceBundle getResourceBundle(final Locale locale) {
		final var basename = ConfigurationFactory.getInstance().getMessagesBasename();
		return getResourceBundle(basename, locale);
	}

	/**
	 * Returns {@link ResourceBundle} by input locale
	 *
	 * @param basename initial input {@link String} base name to operate by
	 * @param locale   initial input {@link Locale} locale to operate by
	 * @return localized resource bundle
	 */
	public static ResourceBundle getResourceBundle(final String basename, final Locale locale) {
		return ResourceBundle.getBundle(basename, locale);
	}

	/**
	 * Returns {@link String} localized message by input parameters
	 *
	 * @param messageKey initial input {@link String} message key
	 * @param locale     initial input {@link Locale} locale
	 * @param arguments  initial input {@link Object} collection of message arguments
	 * @return localized resource message
	 */
	public String getMessage(final String messageKey, final Locale locale, final Object... arguments) {
		final var formatter = MESSAGE_FORMAT.get();
		final var pattern = getResourceBundle(locale).getString(messageKey);
		formatter.applyPattern(pattern);

		return formatter.format(arguments);
	}

	/**
	 * Returns {@link String} message by input parameters
	 *
	 * @param messageKey initial input {@link String} message key
	 * @param arguments  initial input {@link Object} collection of message arguments
	 * @return resource message
	 */
	public String getMessage(final String messageKey, final Object... arguments) {
		return getMessage(messageKey, LOCALE.get(), arguments);
	}
}
