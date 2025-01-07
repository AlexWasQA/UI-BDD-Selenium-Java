package Step_Definitions;

import Utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Add these imports
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setupMethod() {
        Driver.getDriver().manage().deleteAllCookies();
        System.out.println("All cookies deleted");
        Driver.getDriver().manage().window().maximize();
        log.info("@Before: RUNNING before EACH SCENARIO");
    }

    @BeforeStep
    public void beforeStep() {
        log.info("@BeforeStep: RUNNING BEFORE EACH STEP");
    }

    @AfterStep
    public void afterStep() {
        log.info("@AfterStep: RUNNING AFTER EACH STEP");
    }

    @After
    public void teardownMethod(Scenario scenario) {
        log.info("@After: RUNNING AFTER EACH SCENARIO");

        byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());


        //Driver.closeDriver();
        System.out.println("After Scenario is running from Hooks");
    }

    //@AfterAll
    public static void generateReport() {
        File reportOutputDirectory = new File("target/cucumber-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/cucumber.json");

        Configuration configuration = new Configuration(reportOutputDirectory, "Your Project Name");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}