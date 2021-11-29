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
package tech.arenadata.api.test.commons.utils;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.join;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/** General service utilities. */
@Slf4j
@UtilityClass
public class ServiceUtils {

    /**
     * Returns binary flag whether input {@link T} value is positive/negative
     *
     * @param value initial input {@link T} to validate
     * @param <T> type of configurable number
     * @return true - if input value is positive or zero, false - otherwise
     */
    public static <T extends Number> boolean isPositive(final T value) {
        return value.doubleValue() >= 0d;
    }

    /**
     * Returns parsed {@link Optional} {@link String} value
     *
     * @param value initial input {@link String} property value
     * @return {@link Optional} {@link String} value
     */
    public static Optional<String> getPropertyValueAsString(final String value) {
        return getPropertyValue(value, v -> v);
    }

    /**
     * Returns parsed {@link Optional} {@link Long} value
     *
     * @param value initial input {@link String} property value
     * @return {@link Optional} {@link Long} value
     */
    public static Optional<Long> getPropertyValueAsLong(final String value) {
        return getPropertyValue(value, NumberUtils::toLong);
    }

    /**
     * Returns parsed {@link Optional} {@link Integer} value
     *
     * @param value initial input {@link String} property value
     * @return {@link Optional} {@link Integer} value
     */
    public static Optional<Integer> getPropertyValueAsInt(final String value) {
        return getPropertyValue(value, NumberUtils::toInt);
    }

    /**
     * Returns {@link Optional} of computed input {@link String} property value by {@link Function}
     * validator
     *
     * @param <T> type of computed value
     * @param value initial input {@link String} property value to compute
     * @param validator initial input {@link Function} validator
     * @return computed {@link T} property value
     */
    private static <T> Optional<T> getPropertyValue(
            final String value, final Function<String, T> validator) {
        try {
            return ofNullable(value)
                    .map(StringUtils::trimToNull)
                    .filter(StringUtils::isNotEmpty)
                    .map(validator);
        } catch (Exception ex) {
            log.error("Cannot process input property name: {}", value, ex);
        }

        return Optional.empty();
    }

    public static <T> boolean compareArrays(final T[] arr1, final T[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    @SafeVarargs
    public static <T> Stream<T> streamOf(final T... items) {
        return Stream.ofNullable(items).flatMap(Stream::of);
    }

    public static File resourceToFile(final String... paths) {
        final var resourceName = join(paths, File.separator);
        try {
            final var file = new File(resourceName);
            if (!file.exists()) {
                throw new FileNotFoundException(format("Error: file {%s} not found", resourceName));
            }

            return file;
        } catch (Exception ex) {
            log.error("Cannot convert resource with path: {} into file", resourceName, ex);
            throw new IllegalArgumentException(ex);
        }
    }

    public static String resourceToString(final String... paths) {
        final var resourceName = join(paths, File.separator);
        try {
            final var file = new File(resourceName);
            if (!file.exists()) {
                throw new FileNotFoundException(format("Error: file {%s} not found", resourceName));
            }

            return FileUtils.readFileToString(file, UTF_8);
        } catch (Exception ex) {
            log.error("Cannot convert resource with path: {} into string", resourceName, ex);
            throw new IllegalArgumentException(ex);
        }
    }
}
