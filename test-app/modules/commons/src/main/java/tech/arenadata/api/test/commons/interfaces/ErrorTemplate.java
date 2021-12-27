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
package tech.arenadata.api.test.commons.interfaces;

import java.util.Collection;

/** Error template declaration with message code binding */
public interface ErrorTemplate {
    /**
     * Returns {@link E} error template by input {@link String} message code
     *
     * @param <E> type of enumeration
     * @param values initial input {@link E} collection of values
     * @param messageCode initial input {@link String} message code
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
     * @param <E> type of enumeration
     * @param values initial input {@link E} collection of values
     * @param messageKey initial input {@link String} message key
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
