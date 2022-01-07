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

import static java.util.Locale.getDefault;
import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Resource bundle factory that provides resource bundle messages by locale */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourceBundleFactory {

    /** Default resource bundle name */
    public static final String DEFAULT_BUNDLE = "messages";

    /** Resource bundle factory default instance */
    private static final ResourceBundleFactory INSTANCE = new ResourceBundleFactory();

    /**
     * Returns default {@link ResourceBundleFactory} instance
     *
     * @return resource bundle factory
     */
    public static ResourceBundleFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Returns {@link ResourceBundle} by input locale
     *
     * @param locale initial input {@link Locale} locale to operate by
     * @return localized resource bundle
     */
    public ResourceBundle getResourceBundle(final Locale locale) {
        final var basename = ConfigurationFactory.getInstance().getMessagesBasename();
        return getResourceBundle(basename, locale);
    }

    /**
     * Returns {@link ResourceBundle} by input locale
     *
     * @param basename initial input {@link String} base name to operate by
     * @param locale initial input {@link Locale} locale to operate by
     * @return localized resource bundle
     */
    public ResourceBundle getResourceBundle(final String basename, final Locale locale) {
        return getBundle(basename, locale);
    }

    public ResourceBundle getResourceBundle(final String bundle) {
        final var locale = getLocale(bundle);
        try {
            return getBundle(bundle, locale);
        } catch (MissingResourceException exp) {
            return getBundle(DEFAULT_BUNDLE, locale);
        }
    }

    private Locale getLocale(final String bundle) {
        final var first = bundle.indexOf('_');
        final var second = bundle.indexOf('_', first + 1);

        Locale locale;
        if (first != -1) {
            final var language = bundle.substring(first + 1, second);
            final var country = bundle.substring(second + 1);
            return new Locale(language, country);
        }

        return getDefault();
    }
}
