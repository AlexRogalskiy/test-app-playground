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

import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvUtils {
    /** Default env variable pattern */
    private static final Pattern ENV_VAR_PATTERN = Pattern.compile("[$][{]([^}]+)[}]");

    /** Replaces any ${} strings with their corresponding system property. */
    public static String replace(final String val) {
        final var matcher = ENV_VAR_PATTERN.matcher(val);
        final var buf = new StringBuilder();
        while (matcher.find()) {
            final var envVar = matcher.group(1);
            var envVal = System.getProperty(envVar);
            if (envVal == null) envVal = "NOT-SPECIFIED";
            matcher.appendReplacement(buf, envVal.replace("\\", "\\\\"));
        }
        matcher.appendTail(buf);

        return buf.toString();
    }
}
