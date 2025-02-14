package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AKCSearchPage {
    AKCPage akcPage = new AKCPage();

    public AKCSearchPage(){
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }
    @FindBy(xpath = "//div[@class='row comp-event-row conf'][1]")
    public WebElement confirmationEvent;

    @FindBy(xpath = "//*[contains(text(), 'All- Breed and Group (AB/LB)')]")
    public WebElement allBreedAndGroup;

    @FindBy(xpath = "//*[contains(text(), 'Owner-Handled')]")
    public WebElement ownerHandlerType;

    @FindBy (xpath = "//div[@class='check-group event-setting']")
    public WebElement eventLocation;


    @FindBy(xpath = "//*[contains(text(), 'Calendar')]")
    public WebElement eventsCalendarBottomLink;

    @FindBy(id = "//*[@id='PA']")
    public WebElement mapPA;
}
