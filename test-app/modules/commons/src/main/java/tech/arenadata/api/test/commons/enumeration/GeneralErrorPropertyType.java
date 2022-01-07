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

import static tech.arenadata.api.test.commons.factory.ConfigurationConstants.ERROR_TEMPLATE_PREFIX;

import lombok.Getter;
import lombok.ToString;
import tech.arenadata.api.test.commons.interfaces.ErrorProperty;
import tech.arenadata.api.test.extensions.annotation.GenerateResourceBundle;

/** General {@link ErrorProperty} message template codes */
@ToString
@GenerateResourceBundle
public enum GeneralErrorPropertyType implements ErrorProperty {
    /** Unsupported configuration scheme */
    CONFIGURATION_SCHEME_ERROR("configuration.app.scheme.invalid", "Invalid application scheme"),
    /** Unsupported configuration host */
    CONFIGURATION_HOST_ERROR("configuration.app.host.invalid", "Invalid application host"),
    /** Unsupported configuration port */
    CONFIGURATION_PORT_ERROR("configuration.app.port.invalid", "Invalid application port"),
    /** Unsupported environment variable */
    CONFIGURATION_ENV_VAR_ERROR(
            "configuration.app.env.var.invalid", "Invalid application environment variable"),
    /** Unsupported configuration path */
    CONFIGURATION_PATH_ERROR("configuration.app.path.invalid", "Invalid application path"),
    /** Unsupported messages basename */
    CONFIGURATION_MESSAGES_BASENAME_ERROR(
            "configuration.messages.basename.invalid", "Invalid messages basename"),
    /** Unsupported templates basedir */
    CONFIGURATION_TEMPLATES_DIR_ERROR(
            "configuration.templates.basedir.invalid", "Invalid templates base directory"),
    /** Invalid json parse exception */
    DATA_JSON_PARSE_ERROR("data.json.parse.invalid", "Invalid json parsing format"),
    /** Invalid yaml parse exception */
    DATA_YAML_PARSE_ERROR("data.yaml.parse.invalid", "Invalid yaml parsing format");

    /** {@link String} error code */
    @Getter(onMethod_ = {@Override})
    private final String code;
    /** {@link String} error description */
    @Getter(onMethod_ = {@Override})
    private final String description;

    /**
     * Default error template constructor by input {@link String} error code
     *
     * @param code initial input {@link String} code to operate by
     * @param description initial input {@link String} description to operate by
     */
    GeneralErrorPropertyType(final String code, final String description) {
        this.code = ERROR_TEMPLATE_PREFIX + code;
        this.description = description;
    }

    @Override
    public String getKey() {
        return this.code;
    }
}
