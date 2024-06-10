package core;
import helpers.InitDriver;
import helpers.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Базовый класс для инициализации селенида
 */
abstract public class BaseSelenideTest extends InitDriver {


    /**
     * Инициализация selenide с настройками
     */
    public static void setUp(){

        WebDriverManager.chromedriver().setup();
        Configuration.browser = TestConfig.browser;
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserVersion = "120.0.6099.199";
        Configuration.browserSize = "2560x1440";
        Configuration.headless = TestConfig.isHeadless();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=2560,1440");

        Configuration.browserCapabilities = options;

        System.out.println("Chrome version: " + Configuration.browserVersion);
        System.out.println("Headless mode: " + Configuration.headless);
        System.out.println("Browser size: " + Configuration.browserSize);
    }


}
