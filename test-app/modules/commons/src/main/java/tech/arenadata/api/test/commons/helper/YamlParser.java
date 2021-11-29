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
package tech.arenadata.api.test.commons.helper;

import static tech.arenadata.api.test.commons.exception.DataParseException.createYamlParseError;

import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

/** Yaml parser helper */
@Slf4j
@RequiredArgsConstructor
public class YamlParser {

    private final Yaml mapper;

    public YamlParser() {
        this(getDefaultYamlMapper());
    }

    private static Yaml getDefaultYamlMapper() {
        final var representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);

        return new Yaml(representer);
    }

    public Iterable<Object> fromYamlAll(final String yaml) {
        try {
            return this.mapper.loadAll(yaml);
        } catch (Exception ex) {
            log.error("Cannot convert input value={}", yaml, ex);
            throw createYamlParseError(ex);
        }
    }

    public <T> T fromYaml(final String yaml, final Class<T> clazz) {
        try {
            return this.mapper.loadAs(yaml, clazz);
        } catch (Exception ex) {
            log.error("Cannot convert input value={} to class={}", yaml, clazz, ex);
            throw createYamlParseError(ex);
        }
    }

    @SuppressWarnings("TypeParameterUnusedInFormals")
    public <T> T fromYaml(final String yaml) {
        try {
            return this.mapper.load(yaml);
        } catch (Exception ex) {
            log.error("Cannot convert input value={} to class", yaml, ex);
            throw createYamlParseError(ex);
        }
    }

    public <T> String toYaml(final T value) {
        try {
            return this.mapper.dump(value);
        } catch (Exception ex) {
            log.error("Cannot convert input value={} to yaml", value, ex);
            throw createYamlParseError(ex);
        }
    }

    public <T> String toYamlAll(final Iterator<T> values) {
        try {
            return this.mapper.dumpAll(values);
        } catch (Exception ex) {
            log.error("Cannot convert input values to yaml", ex);
            throw createYamlParseError(ex);
        }
    }

    public <T> String toYamlAsMap(final T value) {
        try {
            return this.mapper.dumpAsMap(value);
        } catch (Exception ex) {
            log.error("Cannot convert input value={} to yaml", value, ex);
            throw createYamlParseError(ex);
        }
    }
}
