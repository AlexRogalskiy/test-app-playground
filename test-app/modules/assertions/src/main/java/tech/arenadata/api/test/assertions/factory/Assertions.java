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
package tech.arenadata.api.test.assertions.factory;

import lombok.experimental.UtilityClass;
import org.apache.http.HttpResponse;
import org.assertj.core.util.CheckReturnValue;
import tech.arenadata.api.test.assertions.core.*;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.PageTemplate;
import tech.arenadata.api.test.assertions.model.WebPageTemplate;
import tech.arenadata.api.test.commons.helper.JsonParser;
import tech.arenadata.api.test.commons.helper.YamlParser;

/** Assertion utilities */
@UtilityClass
public class Assertions {

    /** Default custom representation instance */
    public static final CustomRepresentation DEFAULT_REPRESENTATION = new CustomRepresentation();

    /**
     * Returns new http response assert instance
     *
     * @param value initial input {@link HttpResponse} to operate by
     * @return http response assert
     */
    @CheckReturnValue
    public static HttpResponseAssert assertThat(final HttpResponse value) {
        return new HttpResponseAssert(value, new JsonParser());
    }

    /**
     * Returns new item template assert instance
     *
     * @param value initial input {@link ItemTemplate} to operate by
     * @return item template assert
     */
    @CheckReturnValue
    public static ItemTemplateAssert assertThat(final ItemTemplate value) {
        return new ItemTemplateAssert(value);
    }

    /**
     * Returns new page template assert instance
     *
     * @param value initial input {@link PageTemplate} to operate by
     * @return page template assert
     */
    @CheckReturnValue
    public static PageTemplateAssert assertThat(final PageTemplate value) {
        return new PageTemplateAssert(value);
    }

    /**
     * Returns new template content assert instance
     *
     * @param value initial input {@link String} to operate by
     * @return template source assert
     */
    @CheckReturnValue
    public static TemplateContentAssert assertThat(final String value) {
        return new TemplateContentAssert(value, new YamlParser());
    }

    /**
     * Returns new page template view assert instance
     *
     * @param value initial input {@link WebPageTemplate} to operate by
     * @return template source assert
     */
    @CheckReturnValue
    public static WebPageTemplateAssert assertThat(final WebPageTemplate value) {
        return new WebPageTemplateAssert(value);
    }
}
