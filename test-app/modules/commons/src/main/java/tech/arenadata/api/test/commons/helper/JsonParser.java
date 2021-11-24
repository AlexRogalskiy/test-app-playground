package tech.arenadata.api.test.commons.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static tech.arenadata.api.test.commons.exception.DataParseException.createJsonParseError;

/**
 * Json parser helper
 */
@Slf4j
@RequiredArgsConstructor
public class JsonParser {

	private final ObjectMapper mapper;

	public JsonParser() {
		this(getDefaultObjectMapper());
	}

	private static ObjectMapper getDefaultObjectMapper() {
		return new ObjectMapper()
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
			.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
	}

	public String toJson(final Object obj) {
		try {
			return this.mapper.writeValueAsString(obj);
		} catch (JsonProcessingException ex) {
			log.error("Cannot convert input value={} to json", obj, ex);
			throw createJsonParseError(ex);
		}
	}

	public String getField(final String jsonString, final String fieldName) {
		try {
			return this.mapper.readTree(jsonString).findPath(fieldName).asText();
		} catch (JsonProcessingException ex) {
			log.error("Cannot process input value={} by field name={}", jsonString, fieldName, ex);
			throw createJsonParseError(ex);
		}
	}

	public boolean hasField(final String jsonString, final String fieldName) {
		try {
			return this.mapper.readTree(jsonString).has(fieldName);
		} catch (JsonProcessingException ex) {
			log.error("Cannot process input value={} by field name={}", jsonString, fieldName, ex);
			throw createJsonParseError(ex);
		}
	}

	public <T> T fromJson(final String json, final Class<T> clazz) {
		try {
			return this.mapper.readValue(json, clazz);
		} catch (JsonProcessingException ex) {
			log.error("Cannot convert input value={} to class={}", json, clazz, ex);
			throw createJsonParseError(ex);
		}
	}

	public <T> T fromJson(final String json, final TypeReference<T> typeReference) {
		try {
			return this.mapper.readValue(json, typeReference);
		} catch (JsonProcessingException ex) {
			log.error("Cannot convert input value={} to class={}", json, typeReference.getType(), ex);
			throw createJsonParseError(ex);
		}
	}
}
