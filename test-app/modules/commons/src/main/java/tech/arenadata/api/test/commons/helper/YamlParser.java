package tech.arenadata.api.test.commons.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.util.Iterator;

import static tech.arenadata.api.test.commons.exception.DataParseException.createYamlParseError;

/**
 * Yaml parser helper
 */
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
