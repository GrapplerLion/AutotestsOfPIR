package core;
import helpers.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Базовый класс для инициализации селенида
 */
abstract public class BaseSelenideTest extends Driver{

    @BeforeAll
    public static void initDriver() {
//        Driver.initDriver();
    }

    /**
     * Инициализация selenide с настройками
     */
    public void setUp(){
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserVersion = "119.0.6045.124";
        Configuration.browserSize = "2560x1440";
        Configuration.headless = true;

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

    /**
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeEach
    public void init(){
        setUp();
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }

}
