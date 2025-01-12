package Pages;

import Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventsCalendarPage {

    public EventsCalendarPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "input--event-type")
    public WebElement eventTypeDropbox;

    @FindBy(xpath = "//span[contains(text(), 'All-Breed')]")
    public WebElement ConformationAllBreedAndGroupRadioBtn;

    @FindBy(xpath = "//*[@id='control-box--event-type']/div[2]/div[1]/button[2]")
    public WebElement applyBtnEventTypes;

    @FindBy(id = "input--location")
    public WebElement locationDropbox;

    @FindBy(xpath = "//input[@value='PA']")
    public WebElement statePA;

    @FindBy(xpath = "//*[@id='control-box--location']/div[2]/div[1]/button[3]")
    public WebElement applyBtnLocation;

    @FindBy(id = "default_widget")
    public WebElement calendar;

    @FindBy(xpath = "//*[@data-month='3']")
    public WebElement monthMarch;

    @FindBy(xpath = "//div[@class='calendar-form__actions']//button")
    public WebElement findEventBtn;

    @FindBy(id = "calendar-month-year")
    public WebElement calendarMonthYear;

    @FindBy(xpath = "//*[@id='divCalendar']/table/tbody//tr//td")
    public List<WebElement> daysInCalendar;

    @FindBy(xpath = "//div[@class='calendar-day calendar-day--events']")
    public List<WebElement> daysInCalendarWithEvent;

    @FindBy (css = ".calendar-day--events")
    public List<WebElement> eventLinksInCalendar;

    //locators from calendar-table
    @FindBy (xpath = "//*[@class='calendar-list-item__info-misc']")
    public WebElement allBreedConfirmation;

    @FindBy(xpath = "//span[@itemprop = 'addressRegion']")
    public WebElement locationRegionConfirmation;

    @FindBy(xpath = "//div[@class='calendar-list-title']")
    public WebElement eventDateConfirmation;

    public void removingDaysFromDifferentMonth() {
        List<WebElement> currentDays = daysInCalendar.stream()
                .filter(day -> !day.getAttribute("class").contains("not-current"))
                .collect(Collectors.toList());
        List<String> currentDaysText = currentDays.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void extractingDaysWithEvents() {
        for (WebElement dayWithEvent : daysInCalendarWithEvent) {
            WebElement date = dayWithEvent.findElement(By.xpath("./span[1]"));
            String firstSpanText = date.getText();
            WebElement numberOfEvents = dayWithEvent.findElement(By.xpath("./span[2]"));
            String secondSpanText = numberOfEvents.getText();
            WebElement anchorElement = dayWithEvent.findElement(By.xpath("./a"));
        }
    }

    public void clickingEventAndVerifyingCriterias() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        for (int i = 0; i < eventLinksInCalendar.size(); i++) {
            // Re-locate the element to avoid stale element reference
            eventLinksInCalendar = Driver.getDriver().findElements(By.cssSelector(".calendar-day--events"));
            WebElement eventLink = eventLinksInCalendar.get(i);
            wait.until(ExpectedConditions.elementToBeClickable(eventLink)).click();

            // Wait for elements to be visible after clicking
            wait.until(ExpectedConditions.visibilityOf(allBreedConfirmation));
            wait.until(ExpectedConditions.visibilityOf(locationRegionConfirmation));
            wait.until(ExpectedConditions.visibilityOf(eventDateConfirmation));

            // Perform assertions
            assertAll(
                    () -> assertTrue(allBreedConfirmation.isDisplayed(), "All Breed Confirmation is not displayed"),
                    () -> assertTrue(locationRegionConfirmation.isDisplayed(), "Location Region Confirmation is not displayed"),
                    () -> assertTrue(eventDateConfirmation.isDisplayed(), "Event Date Confirmation is not displayed"),
                    () -> assertTrue(allBreedConfirmation.getText().toLowerCase().contains("all breed"), "All Breed text not found"),
                    () -> assertTrue(locationRegionConfirmation.getText().contains("PA"), "PA not found in location"),
                    () -> assertTrue(eventDateConfirmation.getText().toLowerCase().contains("2025"), "Year 2025 not found in date"),
                    () -> assertTrue(eventDateConfirmation.getText().toLowerCase().contains("mar"), "March not found in date")
            );
            Driver.getDriver().navigate().back();
            // Wait for the calendar to reload and be interactive
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".calendar-day--events")));
        }
    }

}



















