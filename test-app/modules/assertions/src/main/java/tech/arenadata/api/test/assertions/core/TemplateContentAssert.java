package tech.arenadata.api.test.assertions.core;

import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.commons.helper.YamlParser;

/**
 * Template source {@link AbstractAssert} implementation provided with following validation rules:
 * - not blank
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
			failWithMessage("expected template content should not be blank but was <%s>", this.actual);
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
