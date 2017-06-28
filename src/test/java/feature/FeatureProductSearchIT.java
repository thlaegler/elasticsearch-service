package feature;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = "src/test/resources/feature",
    plugin = {"pretty", "html:target/cucumber/report", "json:target/cucumber/report.json"})
public class FeatureProductSearchIT {

}
