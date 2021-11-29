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

import static org.apache.commons.lang3.StringUtils.join;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;
import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.PageTemplate;

/**
 * Page template {@link AbstractAssert} implementation provided with following validation rules: -
 * has items by values - has all items by predicate - has no items by predicate - has any items by
 * predicate - has items - has no items - has all unique by predicate
 */
public class PageTemplateAssert extends AbstractAssert<PageTemplateAssert, PageTemplate> {

    public PageTemplateAssert(final PageTemplate template) {
        super(template, PageTemplateAssert.class);
    }

    public PageTemplateAssert hasItems(final ItemTemplate... itemTemplates) {
        this.isNotNull();

        final var items = this.actual.getItems();

        if (!items.containsAll(Arrays.asList(itemTemplates))) {
            failWithMessage(
                    "expected page template should contains <%s> but was <%s>",
                    join(itemTemplates), join(items));
        }

        return this;
    }

    public PageTemplateAssert hasAllItems(final Predicate<? super ItemTemplate> predicate) {
        this.isNotNull();

        final var items = this.actual.getItems();
        if (!items.stream().allMatch(predicate)) {
            failWithMessage("expected page template should match item template predicate");
        }

        return this;
    }

    public PageTemplateAssert hasNoItems(final Predicate<? super ItemTemplate> predicate) {
        this.isNotNull();

        final var items = this.actual.getItems();
        if (items.stream().anyMatch(predicate)) {
            failWithMessage("expected page template should match item template predicate");
        }

        return this;
    }

    public PageTemplateAssert hasAnyItems(final Predicate<? super ItemTemplate> predicate) {
        this.isNotNull();

        final var items = this.actual.getItems();
        if (items.stream().noneMatch(predicate)) {
            failWithMessage("expected page template should match item template predicate");
        }

        return this;
    }

    public PageTemplateAssert hasItems() {
        this.isNotNull();

        final var items = this.actual.getItems();
        if (items.isEmpty()) {
            failWithMessage(
                    "expected page template should contain items but was <%s>", join(items));
        }

        return this;
    }

    public PageTemplateAssert hasNoItems() {
        this.isNotNull();

        final var items = this.actual.getItems();
        if (!items.isEmpty()) {
            failWithMessage(
                    "expected page template should contain no items but was <%s>", join(items));
        }

        return this;
    }

    public <T> PageTemplateAssert hasAllUnique(final Function<ItemTemplate, T> mapper) {
        this.isNotNull();

        final var items = this.actual.getItems();
        if (!items.stream().map(mapper).allMatch(new HashSet<>()::add)) {
            failWithMessage("expected page template should contains unique item templates");
        }

        return this;
    }

    /**
     * Returns {@link ItemTemplate} object by input index.
     *
     * @param index initial input index to fetch by.
     * @return item template
     */
    public ItemTemplate getItem(final int index) {
        this.isNotNull();

        return this.actual.getItems().get(index);
    }
}
