package core;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class TestBaseExtendet {

    @AfterAll
    public static void tearDown() {
        Allure.addAttachment("Allure Report", new ByteArrayInputStream(getAllureReport().getBytes()));
    }

    private static String getAllureReport() {
        ProcessBuilder processBuilder = new ProcessBuilder("allure", "generate", "allure-results", "--clean");
        processBuilder.redirectErrorStream(true);
        Process process;
        try {
            process = processBuilder.start();
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "";
        }
    }


    @BeforeAll
    static void beforeAll() {


    }
}
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//
//        Configuration.browserCapabilities = capabilities;
//    }


