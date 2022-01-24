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

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;
import static tech.arenadata.api.test.commons.utils.ConfigurationUtils.getPropertyMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import tech.arenadata.api.test.commons.interfaces.PropertyTemplate;

/** General {@link PropertyTemplate} message template codes */
public enum PlatformVariables implements Supplier<String> {
    /** Configuration app scheme */
    CONFIG_APP_SCHEME,
    /** Configuration app host */
    CONFIG_APP_HOST,
    /** Configuration app port */
    CONFIG_APP_PORT,
    /** Configuration app path */
    CONFIG_APP_PATH,
    /** Configuration templates directory */
    CONFIG_TEMPLATES_DIR;

    /** Default platform properties */
    private static final Map<PlatformVariables, Object> DEFAULT_PROPERTIES =
            convertToMap(getPropertyMap());

    /**
     * Returns {@link Map} collection of platform properties by input {@link Map} collection of
     * environment properties
     *
     * @param envs initial input {@link Map} collection of environment properties to operate by
     * @return map collection of platform properties
     */
    public static Map<PlatformVariables, Object> convertToMap(final Map<String, Object> envs) {
        return Arrays.stream(values())
                .filter(value -> envs.containsKey(value.get()))
                .collect(
                        collectingAndThen(
                                toMap(Function.identity(), value -> envs.get(value.get())),
                                Collections::unmodifiableMap));
    }

    /**
     * Returns optional {@link String} property value by input {@link PlatformVariables}
     *
     * @param variable initial input {@link PlatformVariables} to operate by
     * @return string property value wrapper
     */
    public static Optional<String> getPlatformProperty(final PlatformVariables variable) {
        return ofNullable(DEFAULT_PROPERTIES.get(variable)).map(Object::toString);
    }

    @Override
    public String get() {
        return this.name();
    }
}
