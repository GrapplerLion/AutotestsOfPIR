package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.localstorage.Item;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.LinkedList;

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
//        NumericFilterEquals();
//        NumericFilterBegins();
    }


}