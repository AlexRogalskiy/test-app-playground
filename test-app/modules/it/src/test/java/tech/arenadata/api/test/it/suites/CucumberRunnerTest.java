package tech.arenadata.api.test.it.suites;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
//@IncludeTags("production")
//@IncludeEngines("junit-jupiter")
//@IncludeEngines("cucumber")
@SuiteDisplayName("TestApiPlayground")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(
	key = GLUE_PROPERTY_NAME,
	value = "tech.arenadata.api.test.it.steps,tech.arenadata.api.test.it.hooks"
)
public class CucumberRunnerTest {
}
