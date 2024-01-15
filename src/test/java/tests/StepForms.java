package tests;

import CollectionColumns.TablePageLS;
import FilterAndSortColumns.FilterColumnsPage;
import HorizontalPanelOptions.NumberOfLine;
import Menu.BaseMenuPir;
import RegPage.RegistrationPage;
import VerticalBarOptions.SettingTable;
import HorizontalPanelOptions.UpdateTable;
import VerticalBarOptions.VerticalBar;
import VerticalBarOptions.XlsFileExport;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import core.BaseSelenideTest;
import io.qameta.allure.Step;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class StepForms extends BaseSelenideTest {


    @Step("Авторизация на сайт")
    public void Authorization(String username, String password) {
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.openPage();
        registrationPage.pirBackground.shouldHave(text("PIR_NN"));
        registrationPage.pirWindowReg.shouldHave(text("Sign in to your account"));
        registrationPage.setFirstName(username);
        registrationPage.setLastName(password);
        registrationPage.setInputLogin();
    }

    @Step("Переход в раздел 'Лицевые счета'")
    public void BaseMenu() {
        BaseMenuPir menuPage = new BaseMenuPir();
        menuPage.dashboard.shouldBe(visible);
        menuPage.openSection();
    }

    @Step("Проверка таблицы, карточки ЛС и их содержимого")
    public void TablePage() {
        TablePageLS tablePageLS = new TablePageLS();
        tablePageLS.columns.shouldHave(size(16));
        tablePageLS.cells.shouldBe(sizeGreaterThan(0));
        tablePageLS.sorting.shouldHave(visible);
        tablePageLS.clientsCard.shouldBe(visible);
        sleep(2000);
        tablePageLS.clientsCard.doubleClick();
        sleep(2000);
        tablePageLS.infoCard.shouldBe(visible);
        String elementText = tablePageLS.infoCard.getText();
        boolean containsInformation = !elementText.isEmpty();
        if (containsInformation) {
            System.out.println("Элемент содержит информацию о клиенте");
        } else {
            System.out.println("Элемент не содержит информацию о клиенте");
        }
    }

    @Step("Настройка колонок")
    public void SettingTable() {
        SettingTable settingTable = new SettingTable();
        settingTable.settingColumn.click();
        settingTable.settingColumnTable.shouldBe(visible);
    }

    @Step("Вертикальный просмотр колонок")
    public void VerticalBar() {
        VerticalBar verticalBar = new VerticalBar();
        verticalBar.stendUpColumn.click();
        verticalBar.columnOfHeader.shouldBe(visible, enabled).shouldHave(text("Информация о записи"));
        verticalBar.allColumnsNumber.shouldHave(size(16));
        verticalBar.allColumnsName.shouldHave(texts("АС", "Подразделение", "Лицевой счет", "Тип абонента", "Состояние ЛС", "Состояние подключения", "Тип здания", "Мобильный телефон", "Стационарный телефон", "Email", "Адрес доставки", "ФИО", "Оборудование", "Наличие прибора учета (счетчика)", "Дата обновления данных по ЛС", "ФИО абонента"));
        verticalBar.stendUpColumn.shouldBe(visible);
    }

    @Step("Обновление таблицы")
    public void UpdateTable() {
        UpdateTable updateTable = new UpdateTable();
        updateTable.getNumberOfAccounts();
        updateTable.updateTableIcon.click();
        updateTable.getNumberOfAccounts();
        System.out.println(updateTable.getNumberOfAccounts());
    }

    @Step("Экспорт файла excel раздела - Лицевые счета")
    public void ExportFileAllTable() throws AWTException {
        XlsFileExport xlsFileExport = new XlsFileExport();
        xlsFileExport.filterAbonent.click();
        xlsFileExport.inputTypeAbonent.click();
        xlsFileExport.inputTypeEquals.click();
        xlsFileExport.flex.shouldBe(visible);
        xlsFileExport.flex.click();
        xlsFileExport.flex.shouldHave(cssClass("is-focus"));
        SelenideElement input = xlsFileExport.flex.find("input");
        input.setValue("Всякие лица");
        sleep(2500);
        xlsFileExport.inputButton.click();
        xlsFileExport.columnsAbonent.shouldBe(visible).getText().contains("Всякие лица");
        xlsFileExport.textOfNumber.shouldBe(text("Всего 1956"));
        xlsFileExport.buttonOfExport.click();
        sleep(5000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @Step("Количество строк на странице")
    public void QuantityOfStrings() {
        NumberOfLine numberOfLine = new NumberOfLine();
        numberOfLine.inputLine.shouldBe(visible, enabled).click();
        numberOfLine.firstLine.shouldBe(visible, enabled).click();
        numberOfLine.tableOfSize.shouldHave(CollectionCondition.size(20));
        numberOfLine.inputLine.shouldBe(visible, enabled).click();
        numberOfLine.secondLine.shouldBe(visible, enabled).click();
        numberOfLine.tableOfSize.shouldHave(CollectionCondition.size(100));
        numberOfLine.inputLine.shouldBe(visible, enabled).click();
        numberOfLine.thirdLine.shouldBe(visible, enabled).click();
        numberOfLine.tableOfSize.shouldHave(CollectionCondition.size(500));
        numberOfLine.inputLine.shouldBe(visible, enabled).click();
        numberOfLine.fourthLine.shouldBe(visible, enabled).click();
        numberOfLine.tableOfSize.shouldHave(CollectionCondition.size(1000));
    }

    //TODO: Модуль фильтрации
    @Step("Модуль фильтрации - строковый('Пусто' и 'Не пусто')")
    public void StringFilterVoid() {
        //TODO:Открытие фильтра "Пусто"
        FilterColumnsPage filterColumnsPage = new FilterColumnsPage();
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.valueFilter.shouldHave(text("Фильтр по значению"));
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter1.shouldBe(visible, enabled).click();
        filterColumnsPage.valueField.shouldHave(size(1));
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldHave(text("Подразделение"));
        filterColumnsPage.tableOfSize.shouldHave(CollectionCondition.size(0));

        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не пусто"
        filterColumnsPage.tableOfSize.shouldHave(CollectionCondition.size(20));
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter2.shouldBe(visible, enabled).click();
        filterColumnsPage.valueField.shouldHave(size(1));
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldHave(text("Подразделение"));
        filterColumnsPage.tableOfSize.shouldHave(CollectionCondition.size(20));

        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - строковый('Равно' и 'Не равно')")
    public void StringFilterEquals() {
        //TODO:проверка фильтра "Равно"
        FilterColumnsPage filterColumnsPage = new FilterColumnsPage();
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter3.shouldBe(visible, enabled).click();
        filterColumnsPage.enterValue.shouldBe(visible, enabled).setValue("ЗАВОЛЖЬЕ");

        boolean value = true;
        SelenideElement firstElement = filterColumnsPage.valueField.first();
        // Проверка, что все элементы, кроме первого, равняются "ЗАВОЛЖЬЕ"
        for (int i = 1; i < filterColumnsPage.valueField.size(); i++) {
            if (!filterColumnsPage.valueField.get(i).shouldHave(matchText("ЗАВОЛЖЬЕ")).is(visible)) {
                value = false;
                break;
            }
        }
        if (!value) {
            // Обработка ошибки, если не все элементы соответствуют условию
            System.out.println("Не все элементы, кроме первого, равняются 'ЗАВОЛЖЬЕ'");
        }

        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldHave(text("Подразделение"));
        filterColumnsPage.containsOfValueColumns2.forEach(element ->
                element.shouldHave(text("ЗАВОЛЖЬЕ")));
        //сброс фильтра
        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();
        //TODO:проверка фильтра "Не равно"
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter4.shouldBe(visible, enabled).click();
        filterColumnsPage.enterValue.shouldBe(visible, enabled).setValue("ЗАВОЛЖЬЕ");
        filterColumnsPage.valueField.forEach(element -> element.shouldNotHave(text("ЗАВОЛЖЬЕ")));
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldHave(text("Подразделение"));
        filterColumnsPage.containsOfValueColumns2.forEach(element ->
                element.shouldNotHave(text("ЗАВОЛЖЬЕ")));

        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - строковый('Начинается c' и 'Не начинается c')")
    public void StringFilterBegins() {
        //TODO:Открытие фильтра "Начинается С"
        FilterColumnsPage filterColumnsPage = new FilterColumnsPage();
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter5.shouldBe(visible, enabled).click();
        filterColumnsPage.enterValue.shouldBe(visible, enabled).setValue("А");

        boolean FirstLetter = true;
        SelenideElement firstElement = filterColumnsPage.valueField.first();
        // Проверка, что все элементы, кроме первого, начинаются на "А"
        for (int i = 1; i < filterColumnsPage.valueField.size(); i++) {
            if (!filterColumnsPage.valueField.get(i).shouldHave(matchText("А.*")).is(visible)) {
                FirstLetter = false;
                break;
            }
        }
        if (!FirstLetter) {
            // Обработка ошибки, если не все элементы соответствуют условию
            System.out.println("Не все элементы, кроме первого, начинаются на 'А'");
        }
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));
        filterColumnsPage.containsOfValueColumns2.forEach(element ->
                element.shouldHave(matchText("А.*")));

        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();
        //TODO:Открытие фильтра "Не начинается С"
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter6.shouldBe(visible, enabled).click();
        filterColumnsPage.enterValue.shouldBe(visible, enabled).setValue("А");

        boolean LastLetter = true;
        SelenideElement firstEl = filterColumnsPage.valueField.first();
        // Проверка, что все элементы, кроме первого, не начинаются с "А"
        for (int i = 1; i < filterColumnsPage.valueField.size(); i++) {
            if (!filterColumnsPage.valueField.get(i).shouldNotHave(matchText("А.*")).is(visible)) {
                LastLetter = false;
                break;
            }
        }
        if (!LastLetter) {
            // Обработка ошибки, если не все элементы соответствуют условию
            System.out.println("Не все элементы, кроме первого, не начинаются с 'А'");
        }
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));
        filterColumnsPage.containsOfValueColumns2.forEach(element ->
                element.shouldNotHave(matchText("А.*")));

        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - строковый('Заканчивается на' и 'Не заканчивается на')")
    public void StringFilterEnds() {
        //TODO:Открытие фильтра "Заканчивается на"
        FilterColumnsPage filterColumnsPage = new FilterColumnsPage();
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter7.shouldBe(visible, enabled).click();
        filterColumnsPage.enterValue.shouldBe(visible, enabled).setValue("1");
        sleep(2000);
        for (int i = 1; i < filterColumnsPage.valueField.size(); i++) {
            String text = filterColumnsPage.valueField.get(i).getText();
            if (!text.endsWith("1")) {
                System.out.println("Элемент с текстом '" + filterColumnsPage.valueField.get(i).getText() + "' не заканчивается на '1'");
            }
        }
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : filterColumnsPage.containsOfValueColumns2) {
            String text = element.getText();
            if (!text.endsWith("1")) {
                System.out.println("Элемент с текстом '" + text + "' не заканчивается на '1'");
            }
        }
        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не заканчивается на"
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter8.shouldBe(visible, enabled).click();
        filterColumnsPage.enterValue.shouldBe(visible, enabled).setValue("1");
        sleep(2000);
        for (int i = 1; i < filterColumnsPage.valueField.size(); i++) {
            String text = filterColumnsPage.valueField.get(i).getText();
            if (text.endsWith("1")) {
                System.out.println("Элемент с текстом '" + filterColumnsPage.valueField.get(i).getText() + "' заканчивается на '1'");
            }
        }
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : filterColumnsPage.containsOfValueColumns2) {
            String text = element.getText();
            if (text.endsWith("1")) {
                System.out.println("Элемент с текстом '" + text + "' заканчивается на '1'");
            }
        }

        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - строковый('Содержит' и 'Не содержит')")
    public void StringFilterContains(){
        //TODO:Открытие фильтра "Содержит"
        FilterColumnsPage filterColumnsPage = new FilterColumnsPage();
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter9.shouldBe(visible, enabled).click();
        filterColumnsPage.enterValue.shouldBe(visible, enabled).setValue("1");
        sleep(2000);

        for (int i = 1; i < filterColumnsPage.valueField.size(); i++) {
            String text = filterColumnsPage.valueField.get(i).getText();
            if (!text.contains("1")) {
                System.out.println("Элемент с текстом '" + filterColumnsPage.valueField.get(i).getText() + "' не cодержит '1'");
            }
        }
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : filterColumnsPage.containsOfValueColumns2) {
            String text = element.getText().toLowerCase();
            if (!text.contains("1")) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит '1'");
            }
        }
        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не содержит"
        filterColumnsPage.filterIcon.shouldBe(visible, enabled).click();
        filterColumnsPage.inputTypeFilter.shouldBe(visible, enabled).click();
        filterColumnsPage.filter10.shouldBe(visible, enabled).click();
        filterColumnsPage.enterValue.shouldBe(visible, enabled).setValue("1");
        sleep(2000);

        for (int i = 1; i < filterColumnsPage.valueField.size(); i++) {
            String text = filterColumnsPage.valueField.get(i).getText();
            if (text.contains("1")) {
                System.out.println("Элемент с текстом '" + filterColumnsPage.valueField.get(i).getText() + "' cодержит '1'");
            }
        }
        filterColumnsPage.applyButton.shouldBe(visible, enabled).click();
        filterColumnsPage.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : filterColumnsPage.containsOfValueColumns2) {
            String text = element.getText().toLowerCase();
            if (text.contains("1")) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит '1'");
            }
        }

        filterColumnsPage.closeFilters.shouldBe(visible, enabled).click();
    }
}

