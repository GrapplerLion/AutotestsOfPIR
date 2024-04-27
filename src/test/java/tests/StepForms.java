package tests;

import CollectionColumns.TablePageLS;
import FilterAndSortColumns.NumericFilterColumns;
import FilterAndSortColumns.StringFilterColumns;
import HorizontalPanelOptions.NumberOfLine;
import Menu.BaseMenuPir;
import RegPage.RegistrationPage;
import VerticalBarOptions.SettingTable;
import HorizontalPanelOptions.UpdateTable;
import VerticalBarOptions.VerticalBar;
import VerticalBarOptions.XlsFileExport;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.BaseSelenideTest;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
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
//        xlsFileExport.searchValue.setValue("Физические лица");
        xlsFileExport.flex.shouldBe(visible);
        xlsFileExport.flex.click();
        xlsFileExport.flex.shouldHave(cssClass("is-focus"));
        SelenideElement input = xlsFileExport.flex.find("input");
        input.setValue("Всякие лица");
        sleep(2500);
        xlsFileExport.inputButton.click();
        xlsFileExport.columnsAbonent.shouldBe(visible).getText().contains("Физические лица");
        xlsFileExport.textOfNumber.shouldBe(text("Всего 748"));
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
        numberOfLine.tableOfSize.shouldHave(CollectionCondition.size(200));
        numberOfLine.inputLine.shouldBe(visible, enabled).click();
        numberOfLine.fourthLine.shouldBe(visible, enabled).click();
        numberOfLine.tableOfSize.shouldHave(CollectionCondition.size(400));
    }

    //TODO: Модуль фильтрации - строковый (колонка "Подразделение", модуль ЛС)
    @Step("Модуль фильтрации - строковый('Пусто' и 'Не пусто')")
    public void StringFilterVoid() {
        //TODO:Открытие фильтра "Пусто"
        StringFilterColumns stringFilterColumns = new StringFilterColumns();
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter1.shouldBe(visible, enabled).click();
        stringFilterColumns.valueOfListDepartament.shouldHave(size(1));
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldHave(text("Подразделение"));
        stringFilterColumns.tableOfSize.shouldHave(CollectionCondition.size(0));

        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не пусто"
        stringFilterColumns.tableOfSize.shouldHave(CollectionCondition.size(20));
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter2.shouldBe(visible, enabled).click();
        stringFilterColumns.valueOfListDepartament.shouldHave(size(1));
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldHave(text("Подразделение"));
        stringFilterColumns.tableOfSize.shouldHave(CollectionCondition.size(20));

        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }

    //TODO: Модуль фильтрации - строковый (колонка "Тип здания", модуль ЛС)
    @Step("Модуль фильтрации - строковый('Равно' и 'Не равно')")
    public void StringFilterEquals() {
        //TODO:проверка фильтра "Равно"
        StringFilterColumns stringFilterColumns = new StringFilterColumns();
        stringFilterColumns.filterIconBuildType.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterBuildType.shouldBe(visible, enabled).click();
        stringFilterColumns.filter3.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterBuildType.shouldBe(visible, enabled).setValue("Частный дом");

        boolean value = true;
        SelenideElement firstElement = stringFilterColumns.valueOfListBuildType.first();
        // Проверка, что все элементы, кроме первого, равняются "Частный дом"
        for (int i = 1; i < stringFilterColumns.valueOfListBuildType.size(); i++) {
            if (!stringFilterColumns.valueOfListBuildType.get(i).shouldHave(matchText("Частный дом")).is(visible)) {
                value = false;
                break;
            }
        }
        if (!value) {
            // Обработка ошибки, если не все элементы соответствуют условию
            System.out.println("Не все элементы, кроме первого, равняются 'Частный дом'");
        }

        stringFilterColumns.applyButtonForFilterBuildType.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldHave(text("Тип здания"));
        stringFilterColumns.containsOfValueColumns7.forEach(element ->
                element.shouldHave(text("Частный дом")));
        //сброс фильтра
        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();
        //TODO:проверка фильтра "Не равно"
        stringFilterColumns.filterIconBuildType.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterBuildType.shouldBe(visible, enabled).click();
        stringFilterColumns.filter4.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterBuildType.shouldBe(visible, enabled).setValue("Частный дом");
        stringFilterColumns.valueOfListBuildType.forEach(element -> element.shouldNotHave(text("Частный дом")));
        stringFilterColumns.applyButtonForFilterBuildType.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldHave(text("Тип здания"));
        stringFilterColumns.containsOfValueColumns7.forEach(element ->
                element.shouldNotHave(text("Частный дом")));

        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - строковый('Начинается c' и 'Не начинается c')")
    public void StringFilterBegins() {
        //TODO:Открытие фильтра "Начинается С"
        StringFilterColumns stringFilterColumns = new StringFilterColumns();
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter5.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterDepartament.shouldBe(visible, enabled).setValue("А");

        boolean FirstLetter = true;
        SelenideElement firstElement = stringFilterColumns.valueOfListDepartament.first();
        // Проверка, что все элементы, кроме первого, начинаются на "А"
        for (int i = 1; i < stringFilterColumns.valueOfListDepartament.size(); i++) {
            if (!stringFilterColumns.valueOfListDepartament.get(i).shouldHave(matchText("А.*")).is(visible)) {
                FirstLetter = false;
                break;
            }
        }
        if (!FirstLetter) {
            // Обработка ошибки, если не все элементы соответствуют условию
            System.out.println("Не все элементы, кроме первого, начинаются на 'А'");
        }
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldHave(text("Подразделение"));
        stringFilterColumns.containsOfValueColumns2.forEach(element ->
                element.shouldHave(matchText("А.*")));

        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();


        //TODO:Открытие фильтра "Не начинается С"
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter6.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterDepartament.shouldBe(visible, enabled).setValue("А");

        boolean LastLetter = true;
        SelenideElement firstEl = stringFilterColumns.valueOfListDepartament.first();
        // Проверка, что все элементы, кроме первого, не начинаются с "А"
        for (int i = 1; i < stringFilterColumns.valueOfListDepartament.size(); i++) {
            if (!stringFilterColumns.valueOfListDepartament.get(i).shouldNotHave(matchText("А.*")).is(visible)) {
                LastLetter = false;
                break;
            }
        }
        if (!LastLetter) {
            // Обработка ошибки, если не все элементы соответствуют условию
            System.out.println("Не все элементы, кроме первого, не начинаются с 'А'");
        }
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));
        stringFilterColumns.containsOfValueColumns2.forEach(element ->
                element.shouldNotHave(matchText("А.*")));

        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - строковый('Заканчивается на' и 'Не заканчивается на')")
    public void StringFilterEnds() {
        //TODO:Открытие фильтра "Заканчивается на"
        StringFilterColumns stringFilterColumns = new StringFilterColumns();
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter7.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterDepartament.shouldBe(visible, enabled).setValue("й");
        sleep(2000);
        for (int i = 1; i < stringFilterColumns.valueOfListDepartament.size(); i++) {
            String text = stringFilterColumns.valueOfListDepartament.get(i).getText();
            if (!text.endsWith("й")) {
                System.out.println("Элемент с текстом '" + stringFilterColumns.valueOfListDepartament.get(i).getText() + "' не заканчивается на 'й'");
            }
        }
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : stringFilterColumns.containsOfValueColumns2) {
            String text = element.getText();
            if (!text.endsWith("й")) {
                System.out.println("Элемент с текстом '" + text + "' не заканчивается на 'й'");
            }
        }
        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не заканчивается на"
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter8.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterDepartament.shouldBe(visible, enabled).setValue("й");
        sleep(2000);
        for (int i = 1; i < stringFilterColumns.valueOfListDepartament.size(); i++) {
            String text = stringFilterColumns.valueOfListDepartament.get(i).getText();
            if (text.endsWith("й")) {
                System.out.println("Элемент с текстом '" + stringFilterColumns.valueOfListDepartament.get(i).getText() + "' заканчивается на 'й'");
            }
        }
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : stringFilterColumns.containsOfValueColumns2) {
            String text = element.getText();
            if (text.endsWith("й")) {
                System.out.println("Элемент с текстом '" + text + "' заканчивается на 'й'");
            }
        }

        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - строковый('Содержит' и 'Не содержит')")
    public void StringFilterContains(){
        //TODO:Открытие фильтра "Содержит"
        StringFilterColumns stringFilterColumns = new StringFilterColumns();
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter9.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterDepartament.shouldBe(visible, enabled).setValue("с");
        sleep(2000);

        for (int i = 1; i < stringFilterColumns.valueOfListDepartament.size(); i++) {
            String text = stringFilterColumns.valueOfListDepartament.get(i).getText();
            if (!text.contains("с")) {
                System.out.println("Элемент с текстом '" + stringFilterColumns.valueOfListDepartament.get(i).getText() + "' не cодержит 'с'");
            }
        }
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : stringFilterColumns.containsOfValueColumns2) {
            String text = element.getText().toLowerCase();
            if (!text.contains("с")) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит 'с'");
            }
        }
        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не содержит"
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter10.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterDepartament.shouldBe(visible, enabled).setValue("с");
        sleep(2000);

        for (int i = 1; i < stringFilterColumns.valueOfListDepartament.size(); i++) {
            String text = stringFilterColumns.valueOfListDepartament.get(i).getText();
            if (text.contains("с")) {
                System.out.println("Элемент с текстом '" + stringFilterColumns.valueOfListDepartament.get(i).getText() + "' cодержит 'с'");
            }
        }
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : stringFilterColumns.containsOfValueColumns2) {
            String text = element.getText().toLowerCase();
            if (text.contains("с")) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит 'с'");
            }
        }

        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }

    //TODO: Модуль фильтрации - числовой
    @Step("Модуль фильтрации - числовой('Пусто' и 'Не пусто')")
    public void NumericFilterVoid() {
        NumericFilterColumns numericFilterColumns = new NumericFilterColumns();
        numericFilterColumns.filterIcon.click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter1.shouldBe(visible, enabled).click();
        numericFilterColumns.valueField.$$("label").get(1).shouldHave(text("(Пустые)"));
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(row -> {
            // Получаем все ячейки восьмой колонки в текущей строке
            row.$$("td").get(7).shouldBe(empty);
        });

        numericFilterColumns.closeFiltersMobilePhone.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не пусто"

        numericFilterColumns.filterIcon.shouldBe(visible, enabled).click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter2.shouldBe(visible, enabled).click();
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldHave(text("Мобильный телефон"));
        try {
            numericFilterColumns.tableOfSize.forEach(row -> {
                // Получаем все ячейки восьмой колонки в текущей строке
                row.$$("td").get(7).shouldNotBe(empty);
            });
        }catch (StaleElementReferenceException e){
            numericFilterColumns.tableOfSize.forEach(row -> {
                // Получаем все ячейки восьмой колонки в текущей строке
                row.$$("td").get(7).shouldNotBe(empty);
            });
        }

        numericFilterColumns.closeFiltersMobilePhone.shouldBe(visible, enabled).click();
    }
}

