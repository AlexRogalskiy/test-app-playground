package tech.arenadata.api.test.it.suites;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SuiteDisplayName("RestApiIssuesPlayground")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@rest-api-issues")
@ConfigurationParameter(
	key = GLUE_PROPERTY_NAME,
	value = "tech.arenadata.api.test.it.steps,tech.arenadata.api.test.it.hooks"
)
public class CucumberIssueRunnerTest {
}
