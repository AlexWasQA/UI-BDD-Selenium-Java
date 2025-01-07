package Step_Definitions;

import Pages.GooglePage;
import Utilities.Configuration_Reader;
import Utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class GoogleSearch_Steps {
    GooglePage googlePage = new GooglePage();
    @Given("user open google without log in")
    public void user_open_google_without_log_in() {
        Driver.getDriver().get(Configuration_Reader.getProperty("url"));
        Driver.getDriver().navigate().refresh();
    }

    @When("user types {string} in search box")
    public void user_types_in_search_box(String string) {
        googlePage.searchBox.sendKeys(string+ Keys.ENTER);
    }
    @Then("user should see results related to {string} on the page")
    public void user_should_see_results_related_to_on_the_page(String string) {
        // by title
        assertTrue(Driver.getDriver().getTitle().equals(string + " - Google Search"));

        //by contains search keyword in each link's titles

        List<WebElement> links = Driver.getDriver().findElements(By.xpath("//a//h3"));
        // Verify each link's name contains the keyword
        for (WebElement link : links) {
            String linkText = link.getText().toLowerCase();
            if (linkText.contains(string.toLowerCase())) {
                System.out.println("Link text contains the keyword: " + linkText);
            } else {
                System.out.println("Link text does not contain the keyword: " + linkText);
            }
        }
    }
}
