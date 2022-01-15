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

/** General property template declaration with key, code and description */
@FunctionalInterface
public interface PropertyTemplate {
    /**
     * Returns {@link E} property template by input {@link String} code
     *
     * @param <E> type of enumeration
     * @param values initial input {@link E} collection of values
     * @param messageCode initial input {@link String} message code
     * @return {@link E} property template
     */
    static <E extends PropertyTemplate> E findByCode(
            final Collection<E> values, final String messageCode) {
        return values.stream()
                .filter(type -> type.getCode().equalsIgnoreCase(messageCode))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns {@link E} property template by input {@link String} key
     *
     * @param <E> type of enumeration
     * @param values initial input {@link E} collection of values
     * @param messageKey initial input {@link String} message key
     * @return {@link E} property template
     */
    static <E extends PropertyTemplate> E findByKey(
            final Collection<E> values, final String messageKey) {
        return values.stream()
                .filter(type -> type.getKey().equalsIgnoreCase(messageKey))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns {@link String} property key
     *
     * @return property key
     */
    String getKey();

    /**
     * Returns {@link String} property code
     *
     * @return property code
     */
    default String getCode() {
        return this.getKey();
    }

    /**
     * Returns {@link String} property description
     *
     * @return property description
     */
    default String getDescription() {
        return this.getKey();
    }
}
