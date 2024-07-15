package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import tests.steps.StepFormsFilterLS;
import tests.steps.StepFormsSorting;

import java.awt.*;
import java.io.IOException;

public class PersonalAccountTestCase {
    
    private final StepFormsFilterLS stepFormsFilterLS = new StepFormsFilterLS();
    private final StepFormsSorting stepFormsSorting = new StepFormsSorting();

    @Test
    @Feature("Тестирование модуля Лицевые счета")
    public void testPersonalAccount() {
        stepFormsFilterLS.Authorization("admin", "admin");
        stepFormsFilterLS.BaseMenu(1);
        stepFormsFilterLS.TablePage();
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
    @Feature("Сортировка всех колонок раздела 'Лицевые счета'")
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
    public void tests() throws IOException {
        stepFormsFilterLS.Authorization("admin", "admin");
        stepFormsFilterLS.BaseMenu(1);
        main.testColumnFilters();
    }


}
