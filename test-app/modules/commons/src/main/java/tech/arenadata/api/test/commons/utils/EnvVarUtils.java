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

import static java.util.Optional.ofNullable;
import static tech.arenadata.api.test.commons.utils.ConfigurationUtils.getPropertySupplier;

import java.util.regex.Pattern;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import tech.arenadata.api.test.commons.enumeration.ConfigPropertyType;
import tech.arenadata.api.test.commons.enumeration.PlatformVariables;
import tech.arenadata.api.test.commons.exception.ConfigurationException;
import tech.arenadata.api.test.commons.exception.PropertyNotFoundException;

/** Utils for handling system environment variables */
@Slf4j
@UtilityClass
public class EnvVarUtils {

    /** Default environment variable pattern */
    private static final String ENV_VAR_PATTERN = "\\$\\{(ENV:)*(.*?)}";

    /**
     * Get a Java system property or system environment variable with the specified name. If a
     * variable with the same name exists in both targets the Java system property is returned.
     *
     * @param name the name of the environment variable
     * @return the value of the environment variable with the specified name
     */
    public static String getValue(@NonNull final String name) {
        // also check java properties if system variable is not found
        final var systemProperty = System.getProperty(name);
        if (systemProperty != null) {
            return systemProperty;
        }

        return System.getenv(name);
    }

    /**
     * Replaces placeholders like '${VAR_NAME}' and '${ENV:VAR_NAME}' with the according environment
     * variables.
     *
     * @param text the text which contains placeholders (or not)
     * @return the text with all the placeholders replaced
     * @throws ConfigurationException if a variable used in a placeholder is not set
     */
    public static String replaceEnvironmentVariablePlaceholders(@NonNull final String text) {
        final var resultString = new StringBuilder();
        final var matcher = Pattern.compile(ENV_VAR_PATTERN).matcher(text);

        while (matcher.find()) {
            if (matcher.groupCount() < 2) {
                // this should never happen as we declared 2 groups in the ENV_VAR_PATTERN
                log.warn("Found unexpected environment variable placeholder in config.xml");
                matcher.appendReplacement(resultString, "");
                continue;
            }

            final var varName = matcher.group(2);
            final var replacement = getValue(varName);

            if (replacement == null) {
                log.error("Environment Variable {} for HiveMQ config.xml is not set.", varName);
                throw new PropertyNotFoundException(varName);
            }

            // sets replacement for this match
            matcher.appendReplacement(resultString, escapeReplacement(replacement));
        }

        // adds everything except the replacements to the string buffer
        matcher.appendTail(resultString);

        return resultString.toString();
    }

    public static String getEnvProperty(
            final PlatformVariables property, final ConfigPropertyType propertyType) {
        return ofNullable(getValue(property.get())).orElseGet(getPropertySupplier(propertyType));
    }

    private static String escapeReplacement(@NonNull final String replacement) {
        return replacement.replace("\\", "\\\\").replace("$", "\\$");
    }
}
