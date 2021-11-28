package tech.arenadata.api.test.it.hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_VERSION;

@Slf4j
public class ScenarioInterceptorHooks {

	@BeforeAll
	public static void beforeScenarioAll() throws MalformedURLException {
		final var capabilities = new DesiredCapabilities();
		capabilities.setCapability(BROWSER_NAME, Browser.CHROME.browserName());
		capabilities.setCapability(BROWSER_VERSION, "91.0");
		capabilities.setCapability("selenoid:options", Map.of(
			"enableVNC", true,
			"enableVideo", false
		));

		final var driver = new RemoteWebDriver(
			URI.create("http://localhost:4444/wd/hub").toURL(),
			capabilities
		);

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
