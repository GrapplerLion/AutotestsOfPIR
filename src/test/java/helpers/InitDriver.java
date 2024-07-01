package helpers;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.time.Duration;

abstract public class InitDriver {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static void setUp() {


//         Set up WebDriver based on the specified browser
        switch (TestConfig.browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + TestConfig.browser);
        }

        // Common configuration settings
        Configuration.browser = TestConfig.browser;
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserVersion = "126.0.6478.127";
        Configuration.browserSize = "2560x1440";
        Configuration.headless = TestConfig.isHeadless();

//        // Set up WebDriver timeouts
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void tearDown() {
        // Close WebDriver and clean up if needed
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


