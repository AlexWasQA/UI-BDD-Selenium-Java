package Step_Definitions;

import Pages.GooglePage;
import Utilities.Configuration_Reader;
import Utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class ParallelTesting_Steps {
    GooglePage googlePage = new GooglePage();
    @Given("the user is on the Google home page")
    public void the_user_is_on_the_google_home_page() {
        Driver.getInstance().getDriver().get(Configuration_Reader.getProperty("url"));
        Driver.getInstance().getDriver().navigate().refresh();
    }

    @When("the user enters {string} into the search bar")
    public void the_user_enters_into_the_search_bar(String string) {
        googlePage.searchBox.sendKeys(string + Keys.ENTER);
    }

    @Then("results related to {string} are shown on the results page")
    public void results_related_to_are_shown_on_the_results_page(String string) {
        Driver.getInstance().getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertTrue(Driver.getInstance().getDriver().getTitle().equals(string + " - Google Search"));

    }
}