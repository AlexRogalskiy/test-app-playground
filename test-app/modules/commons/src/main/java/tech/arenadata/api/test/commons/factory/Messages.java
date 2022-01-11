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
import tech.arenadata.api.test.commons.helper.LocaleHolder;

/** Messages interface declaration */
@FunctionalInterface
public interface Messages {

    /**
     * Returns {@link String} message by input parameters
     *
     * @param locale initial input {@link Locale} message locale
     * @param code initial input {@link String} message key
     * @param values initial input {@link Object} collection of message arguments
     * @return resource message
     */
    String getMessage(final Locale locale, final String code, final Object... values);

    /**
     * Returns {@link String} message by input parameters
     *
     * @param code initial input {@link String} message key
     * @param values initial input {@link Object} collection of message arguments
     * @return resource message
     */
    default String getMessage(final String code, final Object... values) {
        return this.getMessage(LocaleHolder.get(), code, values);
    }
}
