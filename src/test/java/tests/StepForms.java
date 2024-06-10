package tests;

import CollectionColumns.TablePageLS;
import FilterAndSortColumns.ColumnSorting;
import FilterAndSortColumns.LogicalFilterColumns;
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
import com.codeborne.selenide.SelenideElement;
import core.BaseSelenideTest;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        xlsFileExport.filterIconForTypeHouse.click();
        xlsFileExport.inputSearchValue.setValue("Частный дом");
        xlsFileExport.inputButton.click();
        xlsFileExport.tableOfSize.forEach(element ->
                element.shouldHave(text("Частный дом")));
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

    @Step("Сортировка колонок")
    public void SortOfColumns() {
        ColumnSorting columnSorting = new ColumnSorting();

        //получение коллекции до сортировки
        List<String> originalValues = new ArrayList<>();
        for (SelenideElement element : columnSorting.containsOfValueColumns3) {
            originalValues.add(element.getText());
        }

        columnSorting.inputSortLS.click();

        // Получение значений после сортировки по возрастанию
        List<String> sortedAscValues = new ArrayList<>();
        for (SelenideElement element : columnSorting.containsOfValueColumns3) {
            sortedAscValues.add(element.getText());
        }

        // Проверка, что значения отсортированы по возрастанию
        List<String> expectedAscValues = new ArrayList<>(originalValues);
        Collections.sort(expectedAscValues);
        assertEquals(expectedAscValues, sortedAscValues, "Значения должны быть отсортированы в порядке возрастания");

        columnSorting.inputSortLS.click();

        // Получение значений после сортировки по убыванию
        List<String> sortedDescValues = new ArrayList<>();
        for (SelenideElement element : columnSorting.containsOfValueColumns3) {
            sortedDescValues.add(element.getText());
        }

        // Проверка, что значения отсортированы по убыванию
        List<String> expectedDescValues = new ArrayList<>(originalValues);
        Collections.sort(expectedDescValues, Collections.reverseOrder());
        assertEquals(expectedDescValues, sortedDescValues, "Значения должны быть отсортированы в порядке убывания");

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

    @Step("Модуль фильтрации - строковый('Равно' и 'Не равно')")
    public void StringFilterEquals() {
        //TODO:проверка фильтра "Равно"
        StringFilterColumns stringFilterColumns = new StringFilterColumns();
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter3.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterDepartament.shouldBe(visible, enabled).setValue("АП Новострой");

        boolean value = true;
        SelenideElement firstElement = stringFilterColumns.valueOfListDepartament.first();
        // Проверка, что все элементы, кроме первого, равняются "АП Новострой"
        for (int i = 1; i < stringFilterColumns.valueOfListDepartament.size(); i++) {
            if (!stringFilterColumns.valueOfListDepartament.get(i).shouldHave(matchText("АП Новострой")).is(visible)) {
                value = false;
                break;
            }
        }
        if (!value) {
            // Обработка ошибки, если не все элементы соответствуют условию
            System.out.println("Не все элементы, кроме первого, равняются 'АП Новострой'");
        }

        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldHave(text("Подразделение"));
        stringFilterColumns.containsOfValueColumns2.forEach(element ->
                element.shouldHave(text("АП Новострой")));
        //сброс фильтра
        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();

        //TODO:проверка фильтра "Не равно"
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter4.shouldBe(visible, enabled).click();
        stringFilterColumns.enterValueForFilterDepartament.shouldBe(visible, enabled).setValue("АП Новострой");
        stringFilterColumns.valueOfListDepartament.forEach(element -> element.shouldNotHave(text("АП Новострой")));
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldHave(text("Подразделение"));
        stringFilterColumns.containsOfValueColumns7.forEach(element ->
                element.shouldNotHave(text("АП Новострой")));

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

    @Step("Модуль фильтрации - строковый('Включая' и 'Исключая')")
    public void StringFilterMass(){
        //TODO:Открытие фильтра "Включая"
        StringFilterColumns stringFilterColumns = new StringFilterColumns();
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter11.shouldBe(visible, enabled).click();
        stringFilterColumns.valueOfMassFilter.shouldBe(visible, enabled).setValue("АП Новострой \n ТУ Дербентского р-на");
        sleep(2000);
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : stringFilterColumns.containsOfValueColumns2) {
            String text = element.getText().toLowerCase();
            if (!text.contains("АП Новострой, ТУ Дербентского р-на")) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит 'АП Новострой, ТУ Дербентского р-на'");
            }
        }
        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Исключая"
        stringFilterColumns.filterIconForDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.typeFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.filter12.shouldBe(visible, enabled).click();
        stringFilterColumns.valueOfMassFilter.shouldBe(visible, enabled).setValue("АП Новострой \n ТУ Дербентского р-на");
        sleep(2000);
        stringFilterColumns.applyButtonForFilterDepartament.shouldBe(visible, enabled).click();
        stringFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Подразделение"));

        for (SelenideElement element : stringFilterColumns.containsOfValueColumns2) {
            String text = element.getText().toLowerCase();
            if (text.contains("АП Новострой, ТУ Дербентского р-на")) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит 'АП Новострой, ТУ Дербентского р-на'");
            }
        }

        stringFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }



    //TODO: Модуль фильтрации - числовой
    @Step("Модуль фильтрации - числовой('Пусто' и 'Не пусто')")
    public void NumericFilterVoid() {
        //TODO:Открытие фильтра "Пусто"
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

    @Step("Модуль фильтрации - числовой('Равно' и 'Не равно')")
    public void NumericFilterEquals(){
        //TODO:Открытие фильтра "Равно"
        NumericFilterColumns numericFilterColumns = new NumericFilterColumns();
        numericFilterColumns.filterIcon.click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter3.shouldBe(visible, enabled).click();
        numericFilterColumns.enterValue.setValue("84949435104");
        numericFilterColumns.valueField.$$("label").get(1).$(".pir-filter__checkbox-label").shouldHave(text("84949435104"));
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(row -> {
                    // Получаем все ячейки восьмой колонки в текущей строке
                    row.$$("td").get(7).shouldHave(text("84949435104"));
                });
        numericFilterColumns.closeFiltersMobilePhone.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не равно"
        numericFilterColumns.filterIcon.click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter4.shouldBe(visible, enabled).click();
        numericFilterColumns.enterValue.setValue("84949435104");
        numericFilterColumns.valueField.$$("label").forEach(element -> element.shouldNotHave(text("84949435104")));
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(row -> {
                    // Получаем все ячейки восьмой колонки в текущей строке
                    row.$$("td").get(7).shouldNotHave(text("84949435104"));
                });
        numericFilterColumns.closeFiltersMobilePhone.shouldBe(visible, enabled).click();

    }

    @Step("Модуль фильтрации - числовой('Начинается с' и 'Не начинается с')")
    public void NumericFilterBegins(){
        //TODO:Открытие фильтра "Начинается с"
        NumericFilterColumns numericFilterColumns = new NumericFilterColumns();
        numericFilterColumns.filterIcon.click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter5.shouldBe(visible, enabled).click();
        numericFilterColumns.enterValue.setValue("8");
        boolean allButFirstStartsWith8 = true;
        SelenideElement firstElement = numericFilterColumns.valueField.$$("label").first();
        // Проверка, что все элементы, кроме первого, начинаются c "8"
        for (int i = 1; i < numericFilterColumns.valueField.$$("label").size(); i++) {
            String text = numericFilterColumns.valueField.$$("label").get(i).text();
            if (text.startsWith("8")) {
                allButFirstStartsWith8 = false;
                break;
            }
        }
        String firstElementText = firstElement.text();
        if (firstElementText.startsWith("8")) {
            allButFirstStartsWith8 = false;
        }

        if (!allButFirstStartsWith8) {
            // Обработка ошибки
            System.out.println("Не все элементы, кроме первого, не начинаются с '8'");
        }
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(element -> {
                    // Получаем все ячейки восьмой колонки в текущей строке
                    element.$$("td").get(7).shouldHave(matchText("8.*"));
                });
        numericFilterColumns.closeFiltersMobilePhone.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не начинается с"
        numericFilterColumns.filterIcon.click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter6.shouldBe(visible, enabled).click();
        numericFilterColumns.enterValue.setValue("8");
        boolean allDoNotStartsWith8 = true;
        SelenideElement firstElementIsNot8 = numericFilterColumns.valueField.$$("label").first();
        // Проверка, что все элементы, кроме первого, не начинаются c "8"
        for (int i = 1; i < numericFilterColumns.valueField.$$("label").size(); i++) {
            String text = numericFilterColumns.valueField.$$("label").get(i).text();
            if (!text.startsWith("8")) {
                allDoNotStartsWith8 = false;
                break;
            }
        }
        String firstElementIsNot8Text = firstElementIsNot8.text();
        if (!firstElementIsNot8Text.startsWith("8")) {
            allDoNotStartsWith8 = false;
        }

        if (allDoNotStartsWith8) {
            // Обработка ошибки
            System.out.println("Не все элементы, кроме первого, не начинаются с '8'");
        }
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(element -> {
                    // Получаем все ячейки восьмой колонки в текущей строке
                    element.$$("td").get(7).shouldHave(matchText("8.*"));
                });
        numericFilterColumns.closeFiltersMobilePhone.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - числовой('Заканчивается на' и 'Не заканчивается на')")
    public void NumericFilterEnds() {
        //TODO:Открытие фильтра "Заканчивается на"
        NumericFilterColumns numericFilterColumns = new NumericFilterColumns();
        numericFilterColumns.filterIcon.shouldBe(visible, enabled).click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter7.shouldBe(visible, enabled).click();
        numericFilterColumns.enterValue.shouldBe(visible, enabled).setValue("0");

        for (int i = 1; i < numericFilterColumns.valueField.$$("label").size(); i++) {
            String text = numericFilterColumns.valueField.$$("label").get(i).getText();
            if (!text.endsWith("0")) {
                System.out.println("Элемент с текстом '" + numericFilterColumns.valueField.$$("label").get(i).getText() + "' не заканчивается на '0'");
            }
        }
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(element -> {
                    // Получаем все ячейки восьмой колонки в текущей строке
                    element.$$("td").get(7).shouldHave(matchText(".*0$"));
                });
        numericFilterColumns.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не заканчивается на"
        numericFilterColumns.filterIcon.shouldBe(visible, enabled).click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter8.shouldBe(visible, enabled).click();
        numericFilterColumns.enterValue.shouldBe(visible, enabled).setValue("0");
        sleep(2000);
        for (int i = 1; i < numericFilterColumns.valueField.$$("label").size(); i++) {
            String text = numericFilterColumns.valueField.$$("label").get(i).getText();
            if (text.endsWith("0")) {
                System.out.println("Элемент с текстом '" + numericFilterColumns.valueField.$$("label").get(i).getText() + "' заканчивается на '0'");
            }
        }
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(element -> {
                    // Получаем все ячейки восьмой колонки в текущей строке
                    element.$$("td").get(7).shouldNotHave(matchText(".*0$"));
                });

        numericFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - числовой('Содержит' и 'Не содержит')")
    public void NumericFilterContains(){
        //TODO:Открытие фильтра "Содержит"
        NumericFilterColumns numericFilterColumns = new NumericFilterColumns();
        numericFilterColumns.filterIcon.shouldBe(visible, enabled).click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter9.shouldBe(visible, enabled).click();
        numericFilterColumns.enterValue.shouldBe(visible, enabled).setValue("0");
        sleep(2000);

        for (int i = 1; i < numericFilterColumns.valueField.$$("label").size(); i++) {
            String text = numericFilterColumns.valueField.$$("label").get(i).getText();
            if (!text.contains("0")) {
                System.out.println("Элемент с текстом '" + numericFilterColumns.valueField.$$("label").get(i).getText() + "' не cодержит '0'");
            }
        }
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(element -> {
                    // Получаем все ячейки восьмой колонки в текущей строке
                    element.$$("td").get(7).shouldHave(matchText(".*0.*"));
                });
        numericFilterColumns.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Не содержит"
        numericFilterColumns.filterIcon.shouldBe(visible, enabled).click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter10.shouldBe(visible, enabled).click();
        numericFilterColumns.enterValue.shouldBe(visible, enabled).setValue("0");
        sleep(2000);

        for (int i = 1; i < numericFilterColumns.valueField.$$("label").size(); i++) {
            String text = numericFilterColumns.valueField.$$("label").get(i).getText();
            if (text.contains("0")) {
                System.out.println("Элемент с текстом '" + numericFilterColumns.valueField.$$("label").get(i).getText() + "' cодержит '0'");
            }
        }
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Мобильный телефон"));
        numericFilterColumns.tableOfSize
                .forEach(element -> {
                    // Получаем все ячейки восьмой колонки в текущей строке
                    element.$$("td").get(7).shouldNotHave(matchText(".*0.*"));
                });

        numericFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }

    @Step("Модуль фильтрации - числовой('Включая' и 'Исключая')")
    public void NumericFilterMass(){
        //TODO:Открытие фильтра "Включая"
        NumericFilterColumns numericFilterColumns = new NumericFilterColumns();
        numericFilterColumns.filterIcon.shouldBe(visible, enabled).click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter11.shouldBe(visible, enabled).click();
        numericFilterColumns.valueOfMassFilter.shouldBe(visible, enabled).setValue("84949435104 \n ТУ 81769811173");
        sleep(2000);
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Мобильный телефон"));

        for (SelenideElement element : numericFilterColumns.containsOfValueColumns2) {
            String text = element.getText().toLowerCase();
            if (!text.contains("84949435104, 81769811173")) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит '84949435104, 81769811173'");
            }
        }
        numericFilterColumns.closeFilters.shouldBe(visible, enabled).click();

        //TODO:Открытие фильтра "Исключая"
        numericFilterColumns.filterIcon.shouldBe(visible, enabled).click();
        numericFilterColumns.inputTypeFilter.shouldBe(visible, enabled).click();
        numericFilterColumns.filter12.shouldBe(visible, enabled).click();
        numericFilterColumns.valueOfMassFilter.shouldBe(visible, enabled).setValue("84949435104 \n ТУ 81769811173");
        sleep(2000);
        numericFilterColumns.applyButton.shouldBe(visible, enabled).click();
        numericFilterColumns.activeFilters.shouldBe(visible, enabled).shouldHave(text("Мобильный телефон"));

        for (SelenideElement element : numericFilterColumns.containsOfValueColumns2) {
            String text = element.getText().toLowerCase();
            if (text.contains("84949435104, 81769811173")) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит '84949435104, 81769811173'");
            }
        }

        numericFilterColumns.closeFilters.shouldBe(visible, enabled).click();
    }


    //TODO: Модуль фильтрации - логический
    @Step("Модуль фильтрации - логический")
    public void LogicalFilter(){
        LogicalFilterColumns logicalFilterColumns = new LogicalFilterColumns();
        logicalFilterColumns.inputFilter.click();
        logicalFilterColumns.checkBox1.$(byText("Нет")).click();
        logicalFilterColumns.applyButton.click();
        logicalFilterColumns.listOfColumns.forEach(element -> {
            element.$$("td").get(13).shouldHave(text("Да"));
        });
        logicalFilterColumns.closeFilter.click();

        logicalFilterColumns.inputFilter.click();
        logicalFilterColumns.checkBox2.$(byText("Да")).click();
        logicalFilterColumns.applyButton.click();
        logicalFilterColumns.listOfColumns.forEach(element -> {
            element.$$("td").get(13).shouldHave(text("Нет"));
        });
        logicalFilterColumns.closeFilter.click();
    }

    //TODO: Модуль фильтрации - дата

}

