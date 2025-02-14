package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    private static Driver instance;
    private Driver() {}

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static synchronized Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    public WebDriver getDriver(){
        if (driverPool.get()==null){
            String browserType=Configuration_Reader.getProperty("browser");
            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    //options.setBinary("/path/to/chrome");
                    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    driverPool.set(new ChromeDriver(options));
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driverPool.set(new SafariDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                    break;
            }
        }
        return driverPool.get();
    }

    public void closeDriver(){
        if(driverPool.get() !=null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}

