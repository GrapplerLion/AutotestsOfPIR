package tests.SortingTests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import tests.steps.StepFormsFilterLS;
import tests.steps.StepFormsSorting;

public class SortingTestCase {

    private final StepFormsFilterLS stepFormsFilterLS = new StepFormsFilterLS();
    private final StepFormsSorting stepFormsSorting = new StepFormsSorting();
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
}
