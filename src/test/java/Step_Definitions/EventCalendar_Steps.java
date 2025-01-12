package Step_Definitions;

import Pages.AKCSearchPage;
import Pages.BasePage;
import Pages.EventsCalendarPage;
import Utilities.Configuration_Reader;
import Utilities.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventCalendar_Steps {
    BasePage basePage = new BasePage();
    AKCSearchPage akcSearchPage = new AKCSearchPage();
    EventsCalendarPage eventsCalendarPage = new EventsCalendarPage();
    @Given("the user is on the events search page")
    public void the_user_is_on_the_events_search_page() {
        Driver.getDriver().get(Configuration_Reader.getProperty("urlAkcEventSearch"));
        basePage.acceptCookies();
    }
    @When("the user clicks the {string} button at the bottom of the page")
    public void the_user_clicks_the_button_at_the_bottom_of_the_page(String string) {
        assertTrue(akcSearchPage.eventsCalendarBottomLink.isDisplayed());
        assertTrue(akcSearchPage.eventsCalendarBottomLink.isEnabled());
        akcSearchPage.eventsCalendarBottomLink.click();
    }
    @Then("the user should be redirected to the {string} page")
    public void the_user_should_be_redirected_to_the_page(String string) {
        Set<String> windowHandles = Driver.getDriver().getWindowHandles();
        for (String handle : windowHandles) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(Configuration_Reader.getProperty("urlAkcEventsCalendar"))) {
                break;
            }
        }
    }
    @When("the user selects the following event criteria:")
    public void the_user_selects_the_following_event_criteria(DataTable dataTable) {
        List<Map<String, String>> criteria = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : criteria) {
            String criteriaName = row.get("Criteria");
            String criteriaValue = row.get("Value");

            switch (criteriaName) {
                case "Event Type":
                    selectEventType(criteriaValue);
                    break;
                case "State":
                    selectState(criteriaValue);
                    break;
                case "Date":
                    selectDate(criteriaValue);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown criteria: " + criteriaName);
            }
        }
    }

    private void selectEventType(String eventType) {
        eventsCalendarPage.eventTypeDropbox.click();
        eventsCalendarPage.ConformationAllBreedAndGroupRadioBtn.click();
        eventsCalendarPage.applyBtnEventTypes.click();
        System.out.println("Selecting event type: " + eventType);
    }

    private void selectState(String state) {
        eventsCalendarPage.locationDropbox.click();
        eventsCalendarPage.statePA.click();
        eventsCalendarPage.applyBtnLocation.click();
        System.out.println("Selecting state: " + state);
    }

    private void selectDate(String date) {
        eventsCalendarPage.calendar.click();
        eventsCalendarPage.monthMarch.click();
        System.out.println("Selecting date: " + date);
    }

    @When("the user clicks {string} button")
    public void the_user_clicks_button(String string) {
        eventsCalendarPage.findEventBtn.click();
    }

    @Then("the user should see a calendar with marked event quantities for each day")
    public void the_user_should_see_a_calendar_with_marked_event_quantities_for_each_day() {
        assertTrue(eventsCalendarPage.calendarMonthYear.isDisplayed());
        eventsCalendarPage.extractingDaysWithEvents();
    }

    @When("the user clicks on a day with events user should see a list of events matching the selected criteria for that day")
    public void the_user_clicks_on_a_day_with_events_user_should_see_a_list_of_events_matching_the_selected_criteria_for_that_day() {
        eventsCalendarPage.clickingEventAndVerifyingCriterias();
    }
}



