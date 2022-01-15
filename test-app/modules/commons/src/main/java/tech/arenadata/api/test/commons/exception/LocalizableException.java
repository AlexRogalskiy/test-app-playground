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

import lombok.NonNull;
import tech.arenadata.api.test.commons.factory.MessageFactory;
import tech.arenadata.api.test.commons.interfaces.PropertyTemplate;

/** Localizable {@link RuntimeException} implementation */
public class LocalizableException extends RuntimeException {
    /** Default explicit serialVersionUID for interoperability */
    private static final long serialVersionUID = 1802877451104409120L;

    /**
     * {@link LocalizableException} constructor with initial input message
     *
     * @param template initial input {@link PropertyTemplate} message
     * @param args initial input message {@link Object} collection of message arguments
     */
    public LocalizableException(final PropertyTemplate template, final Object... args) {
        this(getLocalizedMessage(template, args));
    }

    /**
     * {@link LocalizableException} constructor with initial input message
     *
     * @param message initial input message {@link String}
     */
    public LocalizableException(final String message) {
        super(message);
    }

    /**
     * {@link LocalizableException} constructor with initial input {@link Throwable}
     *
     * @param cause initial input cause target {@link Throwable}
     */
    public LocalizableException(final Throwable cause) {
        super(cause);
    }

    /**
     * {@link LocalizableException} constructor with initial input message and {@link Throwable}
     *
     * @param message initial input message {@link String}
     * @param cause initial input cause target {@link Throwable}
     */
    public LocalizableException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link LocalizableException} by input parameters
     *
     * @param template initial input {@link PropertyTemplate}
     * @param args initial input {@link Object} collection of message arguments
     * @return {@link LocalizableException}
     */
    @NonNull
    public static String getLocalizedMessage(
            final PropertyTemplate template, final Object... args) {
        return MessageFactory.getInstance().getMessage(template.getCode(), args);
    }
}
