package tech.arenadata.api.test.assertions.model;

import static com.codeborne.selenide.Selenide.page;

/**
 * Web page template model views.
 */
public class WebPageTemplate {

	public WebPageView getView() {
		return page(WebPageView.class);
	}
}
