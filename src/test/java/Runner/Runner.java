package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-reports.html",
                  "rerun:target/failed_rerun.txt",
                  "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                 "json:target/cucumber-reports/cucumber.json"
        },
        features = "src/test/resources/Features",
        glue = "Step_Definitions",
        dryRun = false,
        tags = "@wip",
        publish = true
)
public class Runner {
}
