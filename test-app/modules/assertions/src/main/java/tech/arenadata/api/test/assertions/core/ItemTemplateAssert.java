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

import java.util.Objects;
import java.util.function.Predicate;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.util.introspection.FieldSupport;
import tech.arenadata.api.test.assertions.model.ItemTemplate;

/**
 * Item template {@link AbstractAssert} implementation provided with following validation rules: -
 * item id by value / predicate - item label by value / predicate - item link by value / predicate -
 * item parent id by value / predicate
 */
public class ItemTemplateAssert extends AbstractAssert<ItemTemplateAssert, ItemTemplate> {

    public ItemTemplateAssert(final ItemTemplate template) {
        super(template, ItemTemplateAssert.class);
    }

    public ItemTemplateAssert hasId(final String expectedId) {
        this.isNotNull();

        final var id = FieldSupport.EXTRACTION.fieldValue("id", String.class, this.actual);
        if (!Objects.equals(id, expectedId)) {
            failWithMessage("expected item template id to be <%s> but was <%s>", expectedId, id);
        }

        return this;
    }

    public ItemTemplateAssert hasId(final Predicate<? super String> predicate) {
        this.isNotNull();

        final var id = FieldSupport.EXTRACTION.fieldValue("id", String.class, this.actual);
        if (predicate.negate().test(id)) {
            failWithMessage("expected item template id should match predicate but was <%s>", id);
        }

        return this;
    }

    public ItemTemplateAssert hasLabel(final String expectedLabel) {
        this.isNotNull();

        final var label = FieldSupport.EXTRACTION.fieldValue("label", String.class, this.actual);
        if (!Objects.equals(label, expectedLabel)) {
            failWithMessage(
                    "expected item template label to be <%s> but was <%s>", expectedLabel, label);
        }

        return this;
    }

    public ItemTemplateAssert hasLabel(final Predicate<? super String> predicate) {
        this.isNotNull();

        final var label = FieldSupport.EXTRACTION.fieldValue("label", String.class, this.actual);
        if (predicate.negate().test(label)) {
            failWithMessage(
                    "expected item template label should match predicate but was <%s>", label);
        }

        return this;
    }

    public ItemTemplateAssert hasLink(final String expectedLink) {
        this.isNotNull();

        final var link = FieldSupport.EXTRACTION.fieldValue("link", String.class, this.actual);
        if (!Objects.equals(link, expectedLink)) {
            failWithMessage(
                    "expected item template link to be <%s> but was <%s>", expectedLink, link);
        }

        return this;
    }

    public ItemTemplateAssert hasLink(final Predicate<? super String> predicate) {
        this.isNotNull();

        final var link = FieldSupport.EXTRACTION.fieldValue("link", String.class, this.actual);
        if (predicate.negate().test(link)) {
            failWithMessage(
                    "expected item template link should match predicate but was <%s>", link);
        }

        return this;
    }

    public ItemTemplateAssert hasParentId(final String expectedParentId) {
        this.isNotNull();

        final var parentId =
                FieldSupport.EXTRACTION.fieldValue("depends", String.class, this.actual);
        if (!Objects.equals(parentId, expectedParentId)) {
            failWithMessage(
                    "expected item template parent id to be <%s> but was <%s>",
                    expectedParentId, parentId);
        }

        return this;
    }

    public ItemTemplateAssert hasParentId(final Predicate<? super String> predicate) {
        this.isNotNull();

        final var parentId =
                FieldSupport.EXTRACTION.fieldValue("depends", String.class, this.actual);
        if (predicate.negate().test(parentId)) {
            failWithMessage(
                    "expected item template parent id should match predicate but was <%s>",
                    parentId);
        }
        return this;
    }
}
