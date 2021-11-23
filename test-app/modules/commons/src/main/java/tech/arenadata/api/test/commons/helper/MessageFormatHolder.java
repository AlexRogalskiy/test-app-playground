package tech.arenadata.api.test.commons.helper;

import lombok.experimental.UtilityClass;

import java.text.MessageFormat;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Thread local message format holder
 */
@UtilityClass
public class MessageFormatHolder {
	/**
	 * Thread local {@link MessageFormat} holder with empty value
	 */
	private static final ThreadLocal<MessageFormat> messageFormatHolder = ThreadLocal.withInitial(() -> new MessageFormat(EMPTY));

	/**
	 * Clears the message format associated with the current thread.
	 */
	public static void clear() {
		messageFormatHolder.remove();
	}

	/**
	 * Returns the {@link MessageFormat} associated with the current thread.
	 *
	 * @return message format
	 */
	public static MessageFormat get() {
		return messageFormatHolder.get();
	}

	/**
	 * Associates the passed {@link MessageFormat} with the current thread.
	 *
	 * @param messageFormat initial input {@link MessageFormat} to operate by
	 */
	public static void set(final MessageFormat messageFormat) {
		ofNullable(messageFormat).ifPresent(messageFormatHolder::set);
	}
}
