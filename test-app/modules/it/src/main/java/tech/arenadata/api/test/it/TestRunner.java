package tech.arenadata.api.test.it;

import lombok.extern.slf4j.Slf4j;

import java.security.Permission;
import java.util.stream.Stream;

/**
 * Test runner for cucumber test scenarios.
 */
@Slf4j
public final class TestRunner {

	/**
	 * Default cucumber configuration options
	 */
	private static final String[] DEFAULT_OPTIONS = {
		"--glue", "tech.arenadata.api.test.it.hooks",
		"--glue", "tech.arenadata.api.test.it.steps",
		"--plugin", "pretty",
		"--plugin", "json:report-pretty.json",
		"--tags", "@rest-api",
		"classpath:features"
	};

	public static void main(final String[] args) {
		final var manager = new IgnoreExitCall();
		System.setSecurityManager(manager);

		try {
			final var cucumberOptions = Stream.concat(Stream.of(DEFAULT_OPTIONS), Stream.of(args))
				.toArray(String[]::new);
			io.cucumber.core.cli.Main.run(cucumberOptions);
		} catch (SecurityException ex) {
			log.error("Processing security exception by ignoring exit", ex);
		}
	}

	/**
	 * Security manager patch with ignoring exit call exception
	 */
	private static class IgnoreExitCall extends SecurityManager {

		@Override
		public void checkExit(final int status) {
			//throw new SecurityException();
		}

		@Override
		public void checkPermission(final Permission permission) {
			//request operations not permitted in current context
		}
	}
}
