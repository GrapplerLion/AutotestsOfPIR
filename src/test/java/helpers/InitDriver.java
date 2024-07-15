package helpers;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

abstract public class InitDriver {

    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserVersion = "126.0.6478.127";
        Configuration.browserSize = "2560x1440";

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
            // Add other browsers here as needed
            default:
                throw new IllegalArgumentException("Unsupported browser: " + TestConfig.browser);
        }

        // Установка времени ожидания для драйвера с использованием Duration
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}