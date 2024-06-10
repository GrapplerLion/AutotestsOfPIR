package tests;

import helpers.InitDriver;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class PersonalAccountTestCase extends StepForms {

    @BeforeAll
    public static void setUp() {
        InitDriver.setUp();
    }

    @AfterAll
    public static void tearDown() {
        InitDriver.tearDown();
    }

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
    public void testSettings() throws AWTException {
        Authorization(name, password);
        BaseMenu();
        SettingTable();
        VerticalBar();
        ExportFileAllTable();
        UpdateTable();
        QuantityOfStrings();
    }

    @Test
    @Feature("Тестирование всех функций строкового фильтра колонки 'Подразделение'")
    public void testStringFilter(){
        Authorization(name, password);
        BaseMenu();
        StringFilterVoid();
        StringFilterEquals();
        StringFilterBegins();
        StringFilterEnds();
        StringFilterContains();
        StringFilterMass();
    }

    @Test
    @Feature("Тестирование всех функций числового фильтра колонки 'Мобильный телефон'")
    public void testNumericFilter(){
        Authorization(name, password);
        BaseMenu();
        NumericFilterVoid();
        NumericFilterEquals();
        NumericFilterBegins();
        NumericFilterEnds();
        NumericFilterContains();
        NumericFilterMass();
    }

    @Test
    @Feature("Тестирование логического фильтра поля 'Наличие прибора учета(счетчика)'")
    public void testLogicalFilter(){
        Authorization(name, password);
        BaseMenu();
        LogicalFilter();
    }

    @Test
    @Feature("Тестирование логического фильтра поля 'Наличие прибора учета(счетчика)'")
    public void testDateFilter(){
        Authorization(name, password);
        BaseMenu();
    }


    @Test
    @Feature("Сортировка колонок раздела и подраздела 'Лицевые счета'")
    public void testSortOfColumns(){
        Authorization(name, password);
        BaseMenu();
        SortOfColumns();
    }


}