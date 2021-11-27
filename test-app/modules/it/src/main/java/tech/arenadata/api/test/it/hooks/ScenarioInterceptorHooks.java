package tech.arenadata.api.test.it.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScenarioInterceptorHooks {

	@Before
	public void beforeScenario() {
		log.info(">>> Before running test scenario step >>>");
	}

	@After
	public void afterScenario() {
		log.info(">>> After running test scenario step >>>");
	}
}
