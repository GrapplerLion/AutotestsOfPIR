package helpers;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract public class InitDriver {

    protected static WebDriverWait wait;
    public static void setUp() {

        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();


        Configuration.browser = TestConfig.browser;
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserVersion = "125.0.6422.141";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Configuration.browserSize = "2560x1440";
        Configuration.headless = TestConfig.isHeadless();


        switch (TestConfig.browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                break;
            // Add other browsers here as needed
            default:
                throw new IllegalArgumentException("Unsupported browser: " + TestConfig.browser);
        }
    }

    public static void tearDown() {
        // Close WebDriver and clean up if needed
        }
}
