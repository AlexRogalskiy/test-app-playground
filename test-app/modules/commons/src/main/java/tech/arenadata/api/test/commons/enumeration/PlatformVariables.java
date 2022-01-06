package tech.arenadata.api.test.commons.enumeration;

import tech.arenadata.api.test.commons.interfaces.ErrorProperty;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;
import static tech.arenadata.api.test.commons.utils.ConfigurationUtils.getProperties;

/**
 * General {@link ErrorProperty} message template codes
 */
public enum PlatformVariables implements Supplier<String> {
	/**
	 * Configuration app scheme
	 */
	CONFIG_APP_SCHEME,
	/**
	 * Configuration app host
	 */
	CONFIG_APP_HOST,
	/**
	 * Configuration app port
	 */
	CONFIG_APP_PORT,
	/**
	 * Configuration app path
	 */
	CONFIG_APP_PATH,
	/**
	 * Configuration templates directory
	 */
	CONFIG_TEMPLATES_DIR;

	/**
	 * Default platform properties
	 */
	private static final Map<PlatformVariables, Object> DEFAULT_PROPERTIES = toMap(getProperties());

	/**
	 * Returns {@link Map} collection of platform properties by input {@link Map} collection of environment properties
	 *
	 * @param envs initial input {@link Map} collection of environment properties to operate by
	 * @return map collection of platform properties
	 */
	public static Map<PlatformVariables, Object> toMap(final Map<String, Object> envs) {
		final var map = new EnumMap<PlatformVariables, Object>(PlatformVariables.class);

		for (final var variable : PlatformVariables.values()) {
			final var value = envs.get(variable.get());
			if (value != null) {
				map.put(variable, value);
			}
		}

		return map;
	}

	/**
	 * Returns optional {@link String} property value by input {@link PlatformVariables}
	 *
	 * @param variable initial input {@link PlatformVariables} to operate by
	 * @return string property value wrapper
	 */
	public static Optional<String> getSafeString(final PlatformVariables variable) {
		return ofNullable(DEFAULT_PROPERTIES.get(variable))
			.map(Object::toString);
	}

	@Override
	public String get() {
		return this.name();
	}
}
