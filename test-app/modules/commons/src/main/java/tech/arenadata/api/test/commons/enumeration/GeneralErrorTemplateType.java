package tech.arenadata.api.test.commons.enumeration;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tech.arenadata.api.test.commons.annotation.GenerateResourceBundle;
import tech.arenadata.api.test.commons.interfaces.ErrorTemplate;

/**
 * General {@link ErrorTemplate} message template codes
 */
@ToString
@GenerateResourceBundle
@RequiredArgsConstructor
public enum GeneralErrorTemplateType implements ErrorTemplate {
	/**
	 * Unsupported configuration scheme
	 */
	CONFIGURATION_SCHEME_ERROR("error.configuration.app.scheme.invalid"),
	/**
	 * Unsupported configuration host
	 */
	CONFIGURATION_HOST_ERROR("error.configuration.app.host.invalid"),
	/**
	 * Unsupported configuration port
	 */
	CONFIGURATION_PORT_ERROR("error.configuration.app.port.invalid"),
	/**
	 * Unsupported configuration path
	 */
	CONFIGURATION_PATH_ERROR("error.configuration.app.path.invalid"),
	/**
	 * Unsupported messages basename
	 */
	CONFIGURATION_MESSAGES_BASENAME_ERROR("error.configuration.messages.basename.invalid"),
	/**
	 * Invalid json parse exception
	 */
	DATA_JSON_PARSE_ERROR("error.data.json.parse.invalid");

	/**
	 * {@link String} message code
	 */
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