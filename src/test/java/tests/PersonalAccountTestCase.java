package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.steps.StepFormsFilterLS;
import tests.steps.StepFormsSorting;

import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class PersonalAccountTestCase {

    @BeforeAll
    public static void SetUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "2560x1440");
        Configuration.timeout = 10000;
    }
    private final StepFormsFilterLS stepFormsFilterLS = new StepFormsFilterLS();
    private final StepFormsSorting stepFormsSorting = new StepFormsSorting();

SelenideElement element = $("[data-test-id='pir_filter_action_open_accHoldIndivs_fio']");
    @Test
    @Feature("Тестирование модуля Лицевые счета")
    public void testPersonalAccount() {
        stepFormsFilterLS.Authorization("admin", "admin");
        stepFormsFilterLS.BaseMenu(1);
        if (!element.exists() && !element.isDisplayed()) {
            element.scrollIntoView("{block: 'center', inline: 'nearest'}");
        }
        element.click();
        sleep(10000);
//        stepFormsFilterLS.TablePage();
    }

    @Test
    @Feature("Тестирование опций работы со списком")
    public void testSettings() throws AWTException {
        stepFormsFilterLS.Authorization("admin", "admin");
        stepFormsFilterLS.BaseMenu(1);
        stepFormsFilterLS.SettingTable();
        stepFormsFilterLS.VerticalBar();
        stepFormsFilterLS.ExportFileAllTable();
        stepFormsFilterLS.UpdateTable();
        stepFormsFilterLS.QuantityOfStrings();
    }



    @Test
    @Feature("Сортировка таблиц всех разделов ПИР")
    public void testSortOfColumns(){
        stepFormsFilterLS.Authorization("admin", "admin");
        stepFormsFilterLS.BaseMenu(1);
        stepFormsSorting.SortOfColumnsLS();
        stepFormsFilterLS.BaseMenu(2);
        stepFormsSorting.SortOfColumnsAP();
        stepFormsFilterLS.BaseMenu(3);
        stepFormsSorting.SortOfColumnsDR();
        stepFormsFilterLS.BaseMenu(4);
        stepFormsSorting.SortOfColumnsSP();
        stepFormsFilterLS.BaseMenu(5);
        stepFormsSorting.SortOfColumnsIP();
    }

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
