package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class PersonalAccount extends StepForms  {



    public String name = "admin";
    public String password = "admin";

    @Test
    @Feature("Тестирование модуля Лицевые счета")
    public void testPersonalAccount() {
        Authorization(name, password);
        BaseMenu();
        TablePage();
    }

    @Test
    @Feature("Тестирование опций работы со списком")
    public void testSettings() {
        Authorization(name, password);
        BaseMenu();
        SettingTable();
        VerticalBar();
        ExportFileAllTable();
        UpdateTable();
        QuantityOfStrings();
//        SortOfColumns();
    }

    @Test
    @Feature("Тестирование всех функций строкового фильтра")
    public void testStringFilter(){
        Authorization(name, password);
        BaseMenu();
        StringFilterVoid();
        StringFilterEquals();
        StringFilterBegins();
        StringFilterEnds();
        StringFilterContains();
    }

    @Test
    @Feature("Тестирование всех функций числового фильтра")
    public void testNumericFilter(){
        Authorization(name, password);
        BaseMenu();
//        NumericFilterVoid();
        NumericFilterEquals();
    }
}