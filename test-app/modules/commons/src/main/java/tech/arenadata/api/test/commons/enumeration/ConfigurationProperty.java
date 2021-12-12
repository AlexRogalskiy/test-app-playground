package tech.arenadata.api.test.commons.enumeration;

/**
 * Configuration property declaration
 */
@FunctionalInterface
public interface ConfigurationProperty {
	/**
	 * Returns {@link String} property key
	 *
	 * @return property key
	 */
	String getKey();
}
