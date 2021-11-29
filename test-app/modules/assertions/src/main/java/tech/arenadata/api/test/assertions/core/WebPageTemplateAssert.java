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
package tech.arenadata.api.test.assertions.core;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.streamOf;

import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.WebPageTemplate;

/**
 * Web page template {@link AbstractAssert} implementation provided with following validation rules:
 * - has items - has css class - has not css class - has elements size - has elements css class -
 * has not elements css class
 */
public class WebPageTemplateAssert extends AbstractAssert<WebPageTemplateAssert, WebPageTemplate> {

    public WebPageTemplateAssert(final WebPageTemplate page) {
        super(page, WebPageTemplateAssert.class);
    }

    public WebPageTemplateAssert hasItems(
            final ItemTemplate[] itemTemplates, final String... cssSelectors) {
        this.isNotNull();

        final var page = this.actual.getView();
        final var actual = page.getElementsByCss(cssSelectors);
        final var expected = streamOf(itemTemplates).toArray(ItemTemplate[]::new);
        actual.shouldHave(size(expected.length));

        for (var i = 0; i < actual.size(); i++) {
            final var elem = actual.get(i);
            elem.shouldHave(exactText(expected[i].getLabel()));
            elem.shouldHave(id(expected[i].getId()));
            if (isNotEmpty(expected[i].getLink())) {
                elem.shouldHave(href(expected[i].getLink()));
                elem.shouldNotHave(cssClass("disabled"));
            } else {
                elem.shouldHave(or("href", attribute("href", ""), not(attribute("href"))));
                elem.shouldHave(cssClass("disabled"));
            }
        }

        return this;
    }

    public WebPageTemplateAssert hasSize(final int size, final String... cssSelectors) {
        this.isNotNull();

        final var page = this.actual.getView();
        page.getElementsByCss(cssSelectors).shouldHave(size(size));

        return this;
    }

    public WebPageTemplateAssert hasNoElements(final String... cssSelectors) {
        this.isNotNull();

        final var page = this.actual.getView();
        page.getElementsByCss(cssSelectors).shouldBe(empty);

        return this;
    }

    public WebPageTemplateAssert hasElement(final String tag, final String text) {
        this.isNotNull();

        final var page = this.actual.getView();
        page.getElementByTag(tag).shouldHave(text(text));

        return this;
    }

    public WebPageTemplateAssert hasCssClass(final String cssClass, final String... elementIds) {
        streamOf(elementIds).forEach(elem -> this.hasCssClass(elem, cssClass));
        return this;
    }

    public WebPageTemplateAssert hasCssClass(final String elementId, final String cssClass) {
        this.isNotNull();

        final var page = this.actual.getView();
        page.getElementById(elementId).shouldHave(cssClass(cssClass));

        return this;
    }

    public WebPageTemplateAssert hasNotCssClass(final String cssClass, final String... elementIds) {
        streamOf(elementIds).forEach(elem -> this.hasNotCssClass(elem, cssClass));
        return this;
    }

    public WebPageTemplateAssert hasNotCssClass(final String elementId, final String cssClass) {
        this.isNotNull();

        final var page = this.actual.getView();
        page.getElementById(elementId).shouldNotHave(cssClass(cssClass));

        return this;
    }
}
