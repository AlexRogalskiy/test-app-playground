package tech.arenadata.api.test.assertions.core;

import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.WebPageTemplate;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

/**
 * Web page template {@link AbstractAssert} implementation provided with following validation rules:
 * - has items
 * - has css class
 * - has not css class
 * - has elements size
 * - has elements css class
 * - has not elements css class
 */
public class WebPageTemplateAssert extends AbstractAssert<WebPageTemplateAssert, WebPageTemplate> {

	public WebPageTemplateAssert(final WebPageTemplate page) {
		super(page, WebPageTemplateAssert.class);
	}

	public WebPageTemplateAssert hasItems(final ItemTemplate... itemTemplates) {
		this.isNotNull();

		final var page = this.actual.getView();
		Stream.ofNullable(itemTemplates).flatMap(Stream::of).forEach(item -> {
			final var elem = page.getElementById(item.getId());
			elem.shouldHave(text(item.getLabel()));
			if (isNotEmpty(item.getLink())) {
				elem.shouldHave(href(item.getLink()));
				elem.shouldNotHave(cssClass("disabled"));
			} else {
				elem.shouldNotHave(attribute("href"));
				elem.shouldHave(cssClass("disabled"));
			}
		});

		return this;
	}

	public WebPageTemplateAssert hasSize(final int size, final String... cssSelectors) {
		this.isNotNull();

		final var page = this.actual.getView();
		page.getElementsByCss(cssSelectors).shouldHave(size(size));

		return this;
	}

	public WebPageTemplateAssert hasCssClass(final String cssClass, final String... elementIds) {
		Stream.ofNullable(elementIds).flatMap(Stream::of).forEach(elem -> this.hasCssClass(elem, cssClass));
		return this;
	}

	public WebPageTemplateAssert hasCssClass(final String elementId, final String cssClass) {
		this.isNotNull();

		final var page = this.actual.getView();
		page.getElementById(elementId).shouldHave(cssClass(cssClass));

		return this;
	}

	public WebPageTemplateAssert hasNotCssClass(final String cssClass, final String... elementIds) {
		Stream.ofNullable(elementIds).flatMap(Stream::of).forEach(elem -> this.hasNotCssClass(elem, cssClass));
		return this;
	}

	public WebPageTemplateAssert hasNotCssClass(final String elementId, final String cssClass) {
		this.isNotNull();

		final var page = this.actual.getView();
		page.getElementById(elementId).shouldNotHave(cssClass(cssClass));

		return this;
	}
}
