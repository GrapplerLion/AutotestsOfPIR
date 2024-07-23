package tests.FilterTests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import tests.steps.StepFormsFilterLS;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.sleep;

public class FilterTestCase {

    private final StepFormsFilterLS stepFormsFilterLS = new StepFormsFilterLS();

    private final Main main = new Main();

    @Test
    @Feature("Тестирование фильтров всех разделов ПИР")
    public void testsFilter() throws IOException {
        stepFormsFilterLS.Authorization("admin", "admin");
        stepFormsFilterLS.BaseMenu(1);
        sleep(5000);
        main.testColumnFiltersLS();
        sleep(5000);
        stepFormsFilterLS.BaseMenu(2);
        main.testColumnFiltersAP();
        sleep(5000);
        stepFormsFilterLS.BaseMenu(3);
        main.testColumnFiltersDR();
        sleep(5000);
        stepFormsFilterLS.BaseMenu(4);
        main.testColumnFiltersSP();
        sleep(5000);
        stepFormsFilterLS.BaseMenu(5);
        main.testColumnFiltersIP();
    }

}
