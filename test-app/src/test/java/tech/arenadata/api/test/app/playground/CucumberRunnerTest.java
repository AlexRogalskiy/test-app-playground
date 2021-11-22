package tech.arenadata.api.test.app.playground;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SuiteDisplayName("TestApiPlayground")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(
	key = GLUE_PROPERTY_NAME,
	value = "tech.arenadata.api.test.app.playground.steps,tech.arenadata.api.test.app.playground.hooks"
)
public class CucumberRunnerTest {
}
