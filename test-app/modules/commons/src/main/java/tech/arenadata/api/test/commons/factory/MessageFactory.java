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
package tech.arenadata.api.test.commons.factory;

import java.util.Locale;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tech.arenadata.api.test.commons.helper.MessageFormatHolder;

/** Message factory that provides localized messages by resource bundles */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageFactory implements Messages {

    /** Message factory default instance */
    private static MessageFactory INSTANCE;

    /**
     * Returns default {@link Messages} instance
     *
     * @return message factory
     */
    public static Messages getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MessageFactory();
        }

        return INSTANCE;
    }

    /**
     * Returns {@link String} localized message by input parameters
     *
     * @param messageKey initial input {@link String} message key
     * @param locale initial input {@link Locale} locale
     * @param values initial input {@link Object} collection of message arguments
     * @return localized resource message
     */
    @Override
    public String getMessage(final String messageKey, final Locale locale, final Object... values) {
        final var bundle = ResourceBundleFactory.getInstance().getResourceBundle(locale);
        final var pattern = bundle.getString(messageKey);
        if (values != null && values.length > 0) {
            final var formatter = MessageFormatHolder.get();
            formatter.applyPattern(pattern);
            return formatter.format(values);
        }

        return pattern;
    }
}
