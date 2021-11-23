package tech.arenadata.api.test.assertions.general;

import lombok.experimental.UtilityClass;
import org.apache.http.HttpResponse;
import org.assertj.core.util.CheckReturnValue;
import tech.arenadata.api.test.commons.helper.JsonParser;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.PageTemplate;

/**
 * Assertion utilities
 */
@UtilityClass
public class Assertions {

	/**
	 * Returns new http response assert instance
	 *
	 * @param value initial input {@link HttpResponse} to operate by
	 * @return http response assert
	 */
	@CheckReturnValue
	public static HttpResponseAssert assertThat(final HttpResponse value) {
		return new HttpResponseAssert(value, new JsonParser());
	}

	/**
	 * Returns new item template assert instance
	 *
	 * @param value initial input {@link ItemTemplate} to operate by
	 * @return item template assert
	 */
	@CheckReturnValue
	public static ItemTemplateAssert assertThat(final ItemTemplate value) {
		return new ItemTemplateAssert(value);
	}

	/**
	 * Returns new page template assert instance
	 *
	 * @param value initial input {@link PageTemplate} to operate by
	 * @return page template assert
	 */
	@CheckReturnValue
	public static PageTemplateAssert assertThat(final PageTemplate value) {
		return new PageTemplateAssert(value);
	}
}
