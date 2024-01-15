package proxy;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HttpTraficBrowserTest {

    private static WebDriver driver;
    private static BrowserMobProxy proxy;

    @BeforeClass
    public static void setUp() {
        // Start BrowserMob Proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        // Get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // Configure Chrome to use the proxy
        ChromeOptions options = new ChromeOptions();
        options.setProxy(seleniumProxy);

        // Start Chrome with the configured options
        driver = new ChromeDriver(options);
    }

    @Test
    public void captureHttpTrafficTest() {
        // Enable capturing of request and response content
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // Navigate to your website or perform actions that trigger HTTP requests
        driver.get("http://10.1.115.162:8080/");

        // Stop capturing
        Har har = proxy.getHar();

        // Access captured traffic data from 'har' object
        // You can analyze 'har' to extract information about HTTP requests and responses
    }

    @AfterClass
    public static void tearDown() {  // Сделайте метод статическим
        // Quit the WebDriver and stop BrowserMob Proxy
        driver.quit();
        proxy.stop();
    }
}
