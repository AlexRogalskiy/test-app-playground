package tech.arenadata.api.test.it.hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

@Slf4j
public class ScenarioInterceptorHooks {

	@BeforeAll
	public static void beforeScenarioAll() {
		final var capabilities = new DesiredCapabilities();
		capabilities.setCapability("selenoid:options", Map.of(
			"enableVNC", false,
			"enableVideo", false
		));

		Configuration.browserCapabilities = capabilities;
	}

	@Before
	public void beforeScenario() {
		log.info(">>> Before running test scenario step >>>");
	}

	@After
	public void afterScenario() {
		log.info(">>> After running test scenario step >>>");
	}
}
