package tests.FilterTests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.steps.StepFormsFilterLS;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.sleep;

public class FilterTestCase {

    private final StepFormsFilterLS stepFormsFilterLS = new StepFormsFilterLS();

    private final Main main = new Main();

    @BeforeAll
    public static void SetUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "2560x1440");
        Configuration.timeout = 12000;
        Configuration.pollingInterval = 1000;
    }

    @Test
    @Feature("Тестирование фильтров всех разделов ПИР")
    public void testsFilter() throws IOException, InterruptedException {
        stepFormsFilterLS.Authorization("admin", "admin");
        stepFormsFilterLS.BaseMenu(1);
        main.testColumnFiltersLS();
        stepFormsFilterLS.BaseMenu(2);
        main.testColumnFiltersAP();
        stepFormsFilterLS.BaseMenu(3);
        main.testColumnFiltersDR();
        stepFormsFilterLS.BaseMenu(4);
        main.testColumnFiltersSP();
        stepFormsFilterLS.BaseMenu(5);
        main.testColumnFiltersIP();
    }

}
