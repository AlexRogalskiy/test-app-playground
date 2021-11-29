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
package tech.arenadata.api.test.assertions.model;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.join;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

/** Web page view model selectors. */
public class WebPageView {

    public ElementsCollection getElementsByCss(final String... cssSelectors) {
        return $$(join(cssSelectors, SPACE));
    }

    public SelenideElement getElementByCss(final String cssSelector) {
        return $(cssSelector);
    }

    public SelenideElement getElementById(final String elementId) {
        return $(format("#%s", elementId));
    }

    public SelenideElement getElementByTag(final String elementTag) {
        return $(elementTag);
    }

    public SelenideElement getElementAt(final int index, final String... cssSelectors) {
        return $(join(cssSelectors, SPACE), index);
    }
}
