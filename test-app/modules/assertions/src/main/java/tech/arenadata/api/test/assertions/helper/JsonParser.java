package tech.arenadata.api.test.assertions.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Json parser helper
 */
@Slf4j
@RequiredArgsConstructor
public class JsonParser {

	private final ObjectMapper mapper;

	public JsonParser() {
		this(new ObjectMapper());
	}

	public String toJson(final Object obj) {
		return toJson(this.mapper, obj);
	}

	public String toJson(final ObjectMapper mapper, final Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException ex) {
			log.error("Cannot convert input value={} to json", obj, ex);
			throw new IllegalArgumentException(ex);
		}
	}

	public String getField(final String jsonString, final String fieldName) {
		try {
			return this.mapper.readTree(jsonString).findPath(fieldName).asToken().asString();
		} catch (JsonProcessingException ex) {
			log.error("Cannot process input value={} by field name={}", jsonString, fieldName, ex);
			throw new IllegalArgumentException(ex);
		}
	}

	public boolean hasField(final String jsonString, final String fieldName) {
		try {
			return this.mapper.readTree(jsonString).has(fieldName);
		} catch (JsonProcessingException ex) {
			log.error("Cannot process input value={} by field name={}", jsonString, fieldName, ex);
			throw new IllegalArgumentException(ex);
		}
	}

	public <T> T fromJson(final String json, final Class<T> clazz) {
		try {
			return this.mapper.readValue(json, clazz);
		} catch (JsonProcessingException ex) {
			log.error("Cannot convert input value={} to class={}", json, clazz, ex);
			throw new IllegalArgumentException(ex);
		}
	}

	public <T> T fromJson(final String json, final TypeReference<T> typeReference) {
		try {
			return this.mapper.readValue(json, typeReference);
		} catch (JsonProcessingException ex) {
			log.error("Cannot convert input value={} to class={}", json, typeReference.getType(), ex);
			throw new IllegalArgumentException(ex);
		}
	}
}
