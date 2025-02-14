package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GooglePage {
    public GooglePage(){
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }
    @FindBy(xpath = "//*[@name='callout']")
    public WebElement signIframe;

    @FindBy(xpath = "//*[contains(text(),'Stay signed out')]")
    public WebElement signIframeSign;

    @FindBy(xpath = "//*[@id='APjFqb']")
    public WebElement searchBox;


}
