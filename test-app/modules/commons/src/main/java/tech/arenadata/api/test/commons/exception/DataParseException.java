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

import static tech.arenadata.api.test.commons.enumeration.GeneralErrorTemplateType.DATA_JSON_PARSE_ERROR;
import static tech.arenadata.api.test.commons.enumeration.GeneralErrorTemplateType.DATA_YAML_PARSE_ERROR;

import lombok.NonNull;

/** Data parse {@link RuntimeException} implementation */
public class DataParseException extends RuntimeException {
    /** Default explicit serialVersionUID for interoperability */
    private static final long serialVersionUID = -8863630294671317139L;

    /**
     * {@link DataParseException} constructor with initial input message
     *
     * @param message initial input message {@link String}
     */
    public DataParseException(final String message) {
        super(message);
    }

    /**
     * {@link DataParseException} constructor with initial input {@link Throwable}
     *
     * @param cause initial input cause target {@link Throwable}
     */
    public DataParseException(final Throwable cause) {
        super(cause);
    }

    /**
     * {@link DataParseException} constructor with initial input message and {@link Throwable}
     *
     * @param message initial input message {@link String}
     * @param cause initial input cause target {@link Throwable}
     */
    public DataParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link DataParseException} by input parameters
     *
     * @param message initial input description {@link String}
     * @return {@link DataParseException}
     */
    @NonNull
    public static DataParseException createError(@NonNull final String message) {
        return new DataParseException(message);
    }

    /**
     * Returns json {@link DataParseException} by input parameters
     *
     * @param args initial input message arguments {@link Object}
     * @return json {@link DataParseException}
     */
    @NonNull
    public static DataParseException createJsonParseError(final Object... args) {
        return createError(DATA_JSON_PARSE_ERROR.getLocalizedMessage(args));
    }

    /**
     * Returns yaml {@link DataParseException} by input parameters
     *
     * @param args initial input message arguments {@link Object}
     * @return yaml {@link DataParseException}
     */
    @NonNull
    public static DataParseException createYamlParseError(final Object... args) {
        return createError(DATA_YAML_PARSE_ERROR.getLocalizedMessage(args));
    }
}
