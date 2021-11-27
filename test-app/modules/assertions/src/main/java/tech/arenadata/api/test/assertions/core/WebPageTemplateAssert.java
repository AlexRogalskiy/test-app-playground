package tech.arenadata.api.test.assertions.core;

import org.assertj.core.api.AbstractAssert;
import tech.arenadata.api.test.assertions.model.ItemTemplate;
import tech.arenadata.api.test.assertions.model.WebPageTemplate;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static tech.arenadata.api.test.commons.utils.ServiceUtils.streamOf;

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

	public WebPageTemplateAssert hasItems(final ItemTemplate[] itemTemplates, final String... cssSelectors) {
		this.isNotNull();

		final var page = this.actual.getView();
		final var actual = page.getElementsByCss(cssSelectors);
		final var expected = streamOf(itemTemplates).toArray(ItemTemplate[]::new);
		actual.shouldHave(size(expected.length));

		for (var i = 0; i < actual.size(); i++) {
			final var elem = actual.get(i);
			elem.shouldHave(exactText(expected[i].getLabel()));
			elem.shouldHave(id(expected[i].getId()));
			if (isNotEmpty(expected[i].getLink())) {
				elem.shouldHave(href(expected[i].getLink()));
				elem.shouldNotHave(cssClass("disabled"));
			} else {
				elem.shouldHave(or("href", attribute("href", ""), not(attribute("href"))));
				elem.shouldHave(cssClass("disabled"));
			}
		}

		return this;
	}

	public WebPageTemplateAssert hasSize(final int size, final String... cssSelectors) {
		this.isNotNull();

		final var page = this.actual.getView();
		page.getElementsByCss(cssSelectors).shouldHave(size(size));

		return this;
	}

	public WebPageTemplateAssert hasNoElements(final String... cssSelectors) {
		this.isNotNull();

		final var page = this.actual.getView();
		page.getElementsByCss(cssSelectors).shouldBe(empty);

		return this;
	}

	public WebPageTemplateAssert hasElement(final String tag, final String text) {
		this.isNotNull();

		final var page = this.actual.getView();
		page.getElementByTag(tag).shouldHave(text(text));

		return this;
	}

	public WebPageTemplateAssert hasCssClass(final String cssClass, final String... elementIds) {
		streamOf(elementIds).forEach(elem -> this.hasCssClass(elem, cssClass));
		return this;
	}

	public WebPageTemplateAssert hasCssClass(final String elementId, final String cssClass) {
		this.isNotNull();

		final var page = this.actual.getView();
		page.getElementById(elementId).shouldHave(cssClass(cssClass));

		return this;
	}

	public WebPageTemplateAssert hasNotCssClass(final String cssClass, final String... elementIds) {
		streamOf(elementIds).forEach(elem -> this.hasNotCssClass(elem, cssClass));
		return this;
	}

	public WebPageTemplateAssert hasNotCssClass(final String elementId, final String cssClass) {
		this.isNotNull();

		final var page = this.actual.getView();
		page.getElementById(elementId).shouldNotHave(cssClass(cssClass));

		return this;
	}
}
