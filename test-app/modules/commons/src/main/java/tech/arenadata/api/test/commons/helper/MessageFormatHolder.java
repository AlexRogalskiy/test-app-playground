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
package tech.arenadata.api.test.commons.helper;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.text.MessageFormat;
import lombok.experimental.UtilityClass;

/** Thread local message format holder */
@UtilityClass
public class MessageFormatHolder {
    /** Thread local {@link MessageFormat} holder with empty value */
    private static final ThreadLocal<MessageFormat> messageFormatHolder =
            ThreadLocal.withInitial(() -> new MessageFormat(EMPTY));

    /** Clears the message format associated with the current thread. */
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
