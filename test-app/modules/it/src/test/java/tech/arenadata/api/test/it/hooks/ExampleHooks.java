package tech.arenadata.api.test.it.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class ExampleHooks {

	@BeforeAll
	public static void beforeAllExample() {
		System.out.println("beforeAll");
	}

	@AfterAll
	public static void afterAllExample() {
		System.out.println("afterAll");
	}

	@Before
	public void beforeExample() {
		System.out.println("before");
	}

	@After
	public void afterExample() {
		System.out.println("after");
	}
}
