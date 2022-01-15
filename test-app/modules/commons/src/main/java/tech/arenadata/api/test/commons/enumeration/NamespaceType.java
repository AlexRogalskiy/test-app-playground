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
package tech.arenadata.api.test.commons.enumeration;

import static tech.arenadata.api.test.commons.factory.ConfigurationConstants.PROPERTY_DELIMITER;
import static tech.arenadata.api.test.commons.utils.ConfigurationUtils.getProperty;

import java.util.Arrays;
import java.util.function.Function;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/** Supported namespace prefixes enumeration. */
@Getter
@ToString
@RequiredArgsConstructor
public enum NamespaceType implements Function<String, String> {
    /** Error namespace prefix */
    ERROR("error"),
    /** Configuration namespace prefix */
    CONFIG("config"),
    /** Custom namespace prefix */
    CUSTOM("custom");

    /** Namespace prefix */
    private final String prefix;

    /**
     * Returns {@link NamespaceType} by input {@link String} environment name or default namespace,
     * if not supported
     *
     * @param environmentName initial input {@link String} environment name to operate by
     * @param defaultEnvironment initial input {@link String} default environment name to operate by
     * @return namespace type
     */
    @NonNull
    public static NamespaceType getOrDefault(
            final String environmentName, final NamespaceType defaultEnvironment) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(environmentName))
                .findFirst()
                .orElse(defaultEnvironment);
    }

    /**
     * Returns delimited {@link String} namespace prefix by input {@link String} key
     *
     * @param key initial input {@link String} namespace key to operate by
     * @return delimited namespace prefix
     */
    @Override
    public String apply(final String key) {
        return getProperty(this.prefix + PROPERTY_DELIMITER + key);
    }
}
