package tech.arenadata.api.test.commons.helper;

import lombok.experimental.UtilityClass;

import java.util.Locale;

import static java.util.Optional.ofNullable;

/**
 * Thread local locale holder
 */
@UtilityClass
public class LocaleHolder {
	/**
	 * Thread local {@link Locale} holder with default locale
	 */
	private static final ThreadLocal<Locale> localeHolder = ThreadLocal.withInitial(Locale::getDefault);

	/**
	 * Clears the locale associated with the current thread.
	 */
	public static void clear() {
		localeHolder.remove();
	}

	/**
	 * Returns the {@link Locale} associated with the current thread.
	 *
	 * @return locale
	 */
	public static Locale get() {
		return localeHolder.get();
	}

	/**
	 * Associates the passed {@link Locale} with the current thread.
	 *
	 * @param locale initial input {@link Locale} to operate by
	 */
	public static void set(final Locale locale) {
		ofNullable(locale).ifPresent(localeHolder::set);
	}
}
