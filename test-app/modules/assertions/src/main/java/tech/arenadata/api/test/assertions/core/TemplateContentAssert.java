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

import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.commons.helper.YamlParser;

/**
 * Template source {@link AbstractAssert} implementation provided with following validation rules: -
 * not blank
 */
public class TemplateContentAssert extends AbstractAssert<TemplateContentAssert, String> {

    private final YamlParser yamlParser;

    public TemplateContentAssert(final String value, final YamlParser yamlParser) {
        super(value, TemplateContentAssert.class);
        this.yamlParser = yamlParser;
    }

    public TemplateContentAssert isNotBlank() {
        this.isNotNull();

        if (this.actual.isBlank()) {
            failWithMessage(
                    "expected template content should not be blank but was <%s>", this.actual);
        }

        return this;
    }

    /**
     * Returns {@link T} target template object.
     *
     * @return target object
     */
    @SuppressWarnings("TypeParameterUnusedInFormals")
    public <T> T asPOJO() {
        return this.yamlParser.fromYaml(this.actual);
    }

    /**
     * Returns {@link T} target template object by {@link Class} type.
     *
     * @param clazz initial input {@link Class} to operate by
     * @return target object
     */
    public <T> T asPOJO(final Class<T> clazz) {
        return this.yamlParser.fromYaml(this.actual, clazz);
    }
}
