package tech.arenadata.api.test.commons.exception;

/**
 * A PropertyNotFoundException is an unchecked {@link RuntimeException}.
 * <p>
 * It should be thrown, if getting a ssl certificate property fails.
 */
public class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException(final String message) {
        super(message);
    }

    public PropertyNotFoundException(final Throwable throwable) {
        super(throwable);
    }

    public PropertyNotFoundException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public PropertyNotFoundException(final String message,
                                     final Throwable cause,
                                     final boolean enableSuppression,
                                     final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
