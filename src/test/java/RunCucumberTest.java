import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;


@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value = "pretty, html:target/cucumber-reports/Cucumber.html, json:target/cucumber-reports/Cucumber.json"
)
@ConfigurationParameter(
    key = GLUE_PROPERTY_NAME,
    value = "test.tally.report.steps"
)
@ConfigurationParameter(
    key = FEATURES_PROPERTY_NAME,
    value = "src/test/java/test/tally/report/features"
)

public class RunCucumberTest {

}
