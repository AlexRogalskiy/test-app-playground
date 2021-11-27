package tech.arenadata.api.test.assertions.model;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.join;

/**
 * Web page view model selectors.
 */
public class WebPageView {

	public ElementsCollection getElementsByCss(final String... cssSelectors) {
		return $$(join(cssSelectors, SPACE));
	}

	public SelenideElement getElementByCss(final String cssSelector) {
		return $(cssSelector);
	}

	public SelenideElement getElementById(final String elementId) {
		return $(format("#%s", elementId));
	}

	public SelenideElement getElementByTag(final String elementTag) {
		return $(elementTag);
	}

	public SelenideElement getElementAt(final int index, final String... cssSelectors) {
		return $(join(cssSelectors, SPACE), index);
	}
}
