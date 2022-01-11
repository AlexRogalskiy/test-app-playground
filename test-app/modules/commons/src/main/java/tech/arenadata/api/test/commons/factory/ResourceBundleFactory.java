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

import static java.util.Locale.ENGLISH;
import static java.util.Locale.getDefault;
import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Resource bundle factory that provides resource bundle messages by locale */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourceBundleFactory {

    /** Default resource bundle base name */
    private static final String DEFAULT_BUNDLE_BASE_NAME = "messages";

    /** Default resource bundle locale */
    private static final Locale DEFAULT_BUNDLE_LOCALE = ENGLISH;

    /** Default resource bundle instance */
    private static final ResourceBundle DEFAULT_BUNDLE =
            getBundle(getDefaultBundleBaseName(), getDefaultLocale());

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
     * Returns the default {@link Locale} for the application, which is set to {@link
     * Locale#ENGLISH} since that's the only available {@link ResourceBundle} at time of writing.
     *
     * @return the default {@link Locale}
     */
    public static Locale getDefaultLocale() {
        return DEFAULT_BUNDLE_LOCALE;
    }

    /**
     * Returns the default {@link String} bundle base name
     *
     * @return the default {@link String} bundle base name
     */
    public static String getDefaultBundleBaseName() {
        return DEFAULT_BUNDLE_BASE_NAME;
    }

    /**
     * Returns the {@link ResourceBundle} for the default {@link Locale}.
     *
     * @return the {@link ResourceBundle} for the default {@link Locale}
     */
    public static ResourceBundle getDefaultBundle() {
        return DEFAULT_BUNDLE;
    }

    private static Locale getLocale(final String bundle) {
        final var first = bundle.indexOf('_');
        final var second = bundle.indexOf('_', first + 1);

        if (first != -1) {
            final var language = bundle.substring(first + 1, second);
            final var country = bundle.substring(second + 1);

            return new Locale(language, country);
        }

        return getDefault();
    }

    /**
     * Returns {@link ResourceBundle} by input locale
     *
     * @param locale initial input {@link Locale} locale to operate by
     * @return localized resource bundle
     */
    public ResourceBundle getResourceBundle(final Locale locale) {
        final var basename = ConfigurationFactory.getInstance().getMessagesBasename();

        return this.getResourceBundle(basename, locale);
    }

    /**
     * Returns {@link ResourceBundle} by input locale
     *
     * @param basename initial input {@link String} base name to operate by
     * @param locale initial input {@link Locale} locale to operate by
     * @return localized resource bundle
     */
    public ResourceBundle getResourceBundle(final String basename, final Locale locale) {
        try {
            return getBundle(basename, locale);
        } catch (Exception e) {
            return getDefaultBundle();
        }
    }

    public ResourceBundle getResourceBundle(final String basename) {
        final var locale = getLocale(basename);
        try {
            return this.getResourceBundle(basename, locale);
        } catch (Exception e) {
            return this.getResourceBundle(getDefaultBundleBaseName(), locale);
        }
    }
}
