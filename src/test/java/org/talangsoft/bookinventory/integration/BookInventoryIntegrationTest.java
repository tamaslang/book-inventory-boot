package org.talangsoft.bookinventory.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:bookinventory.integration",
        tags = {"@restApiIntegration", "~@ignore"},
        format = {"html:target/cucumber-report/bookInventoryIntegration",
                "json:target/cucumber-report/bookInventoryIntegration.json"})
public class BookInventoryIntegrationTest {
}
