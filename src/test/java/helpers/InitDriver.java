package helpers;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class InitDriver {

    public static void setUp() {
        // Initialize configuration
        TestConfig.initConfig();

        System.out.println("Browser: " + TestConfig.browser);
        System.out.println("Headless: " + TestConfig.isHeadless());

        // Set settings for selenide browser

        Configuration.browser = TestConfig.browser;
        Configuration.headless = TestConfig.isHeadless();
        Configuration.browserSize = "2560x1440";
        Configuration.webdriverLogsEnabled = true;


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
