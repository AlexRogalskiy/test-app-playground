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
package tech.arenadata.api.test.commons.enumeration;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tech.arenadata.api.test.commons.interfaces.ErrorTemplate;
import tech.arenadata.api.test.extensions.annotation.GenerateResourceBundle;

/** General {@link ErrorTemplate} message template codes */
@ToString
@GenerateResourceBundle
@RequiredArgsConstructor
public enum GeneralErrorTemplateType implements ErrorTemplate {
    /** Unsupported configuration scheme */
    CONFIGURATION_SCHEME_ERROR("error.configuration.app.scheme.invalid"),
    /** Unsupported configuration host */
    CONFIGURATION_HOST_ERROR("error.configuration.app.host.invalid"),
    /** Unsupported configuration port */
    CONFIGURATION_PORT_ERROR("error.configuration.app.port.invalid"),
    /** Unsupported configuration path */
    CONFIGURATION_PATH_ERROR("error.configuration.app.path.invalid"),
    /** Unsupported messages basename */
    CONFIGURATION_MESSAGES_BASENAME_ERROR("error.configuration.messages.basename.invalid"),
    /** Unsupported templates basedir */
    CONFIGURATION_TEMPLATES_DIR_ERROR("error.configuration.templates.basedir.invalid"),
    /** Invalid json parse exception */
    DATA_JSON_PARSE_ERROR("error.data.json.parse.invalid"),
    /** Invalid yaml parse exception */
    DATA_YAML_PARSE_ERROR("error.data.yaml.parse.invalid");

    /** {@link String} message code */
    private final String code;

    @Override
    public String getMessageKey() {
        return this.name();
    }

    @Override
    public String getMessageCode() {
        return this.code;
    }
}
