package tech.arenadata.api.test.assertions.general;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.commons.helper.JsonParser;

import java.util.Objects;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * Http response {@link AbstractAssert} implementation provided with following validation rules:
 * - response status code
 * - response reason phrase
 * - response protocol version
 * - response header / headers
 * - response content
 * - response content length
 * - response field value / field values
 * - response field / fields
 */
@Slf4j
class HttpResponseAssert extends AbstractAssert<HttpResponseAssert, HttpResponse> {

	private final String content;
	private final JsonParser jsonParser;

	HttpResponseAssert(final HttpResponse response, final JsonParser jsonParser) {
		super(response, HttpResponseAssert.class);
		this.content = this.extractContent(response);
		this.jsonParser = jsonParser;
	}

	public HttpResponseAssert hasStatusCode(final int expectedStatusCode) {
		this.isNotNull();

		final var statusCode = this.actual.getStatusLine().getStatusCode();
		if (statusCode != expectedStatusCode) {
			failWithMessage("expected response status code to be <%s> but was <%s>", expectedStatusCode, statusCode);
		}
		return this;
	}

	public HttpResponseAssert hasReasonPhrase(final String expectedReasonPhrase) {
		this.isNotNull();

		final var reasonPhrase = this.actual.getStatusLine().getReasonPhrase();
		if (!Objects.equals(reasonPhrase, expectedReasonPhrase)) {
			failWithMessage("expected response reason phrase to be <%s> but was <%s>", expectedReasonPhrase, reasonPhrase);
		}
		return this;
	}

	public HttpResponseAssert hasProtocolVersion(final ProtocolVersion expectedProtocolVersion) {
		this.isNotNull();

		final var protocolVersion = this.actual.getStatusLine().getProtocolVersion();
		if (!Objects.equals(protocolVersion, expectedProtocolVersion)) {
			failWithMessage("expected response protocol version to be <%s> but was <%s>", expectedProtocolVersion, protocolVersion);
		}
		return this;
	}

	public HttpResponseAssert hasHeader(final String expectedHeader) {
		this.isNotNull();

		if (!this.actual.containsHeader(expectedHeader)) {
			failWithMessage("expected response header to be <%s> but was <%s>", expectedHeader, join(this.actual.getAllHeaders()));
		}
		return this;
	}

	public HttpResponseAssert hasHeaders(final String... expectedHeaders) {
		Stream.of(expectedHeaders).forEach(this::hasHeader);
		return this;
	}

	@SneakyThrows
	public HttpResponseAssert hasContent(final String expectedContent) {
		this.isNotNull();

		if (!Objects.equals(this.content, expectedContent)) {
			failWithMessage("expected response content to be <%s> but was <%s>", expectedContent, content);
		}
		return this;
	}

	public HttpResponseAssert hasContentLength(final long expectedContentLength) {
		this.isNotNull();

		final var contentLength = this.actual.getEntity().getContentLength();
		if (contentLength != expectedContentLength) {
			failWithMessage("expected response content length to be <%s> but was <%s>", expectedContentLength, contentLength);
		}
		return this;
	}

	@SneakyThrows
	public HttpResponseAssert hasFieldValue(final String expectedFieldName, final String expectedFieldValue) {
		this.isNotNull();

		final var fieldValue = this.jsonParser.getField(this.content, expectedFieldName);
		if (!Objects.equals(fieldValue, expectedFieldValue)) {
			failWithMessage("expected response content field value to be <%s> but was <%s>", expectedFieldValue, fieldValue);
		}
		return this;
	}

	public HttpResponseAssert hasFieldValues(final Pair<String, String>... expectedFieldPairs) {
		Stream.of(expectedFieldPairs).forEach(p -> this.hasFieldValue(p.getKey(), p.getValue()));
		return this;
	}

	@SneakyThrows
	public HttpResponseAssert hasField(final String expectedFieldName) {
		this.isNotNull();

		final var fieldNameExists = this.jsonParser.hasField(this.content, expectedFieldName);
		if (!fieldNameExists) {
			failWithMessage("expected response content field name to be <%s> but was none", expectedFieldName);
		}
		return this;
	}

	public HttpResponseAssert hasFields(final String... expectedFieldNames) {
		Stream.of(expectedFieldNames).forEach(this::hasField);
		return this;
	}

	/**
	 * Returns {@link T} object by input target type class.
	 *
	 * @param targetClass initial input {@link Class} target type.
	 * @return target object
	 */
	public <T> T asPOJO(final Class<T> targetClass) {
		return this.jsonParser.fromJson(this.content, targetClass);
	}

	/**
	 * Returns {@link T} object by input target type reference.
	 *
	 * @param typeReference initial input {@link TypeReference} target type.
	 * @return target object
	 */
	public <T> T asPOJO(final TypeReference<T> typeReference) {
		return this.jsonParser.fromJson(this.content, typeReference);
	}

	private String extractContent(final HttpResponse response) {
		try (final var stream = response.getEntity().getContent()) {
			return IOUtils.toString(stream);
		} catch (Exception ex) {
			log.error("Cannot extract content from response", ex);
		}

		return null;
	}
}
