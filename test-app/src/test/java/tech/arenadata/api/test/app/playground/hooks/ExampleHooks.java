package tech.arenadata.api.test.app.playground.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ExampleHooks {
	@Before
	public void beforeExample() {
		System.out.println("beforeExample");
	}

	@After
	public void afterExample() {
		System.out.println("afterExample");
	}
}
