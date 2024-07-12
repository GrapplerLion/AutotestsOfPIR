package tests.steps;

import CollectionColumns.TablePageLS;
import HorizontalPanelOptions.NumberOfLine;
import Menu.BaseMenuPir;
import RegPage.RegistrationPage;
import VerticalBarOptions.SettingTable;
import HorizontalPanelOptions.UpdateTable;
import VerticalBarOptions.VerticalBar;
import VerticalBarOptions.XlsFileExport;
import com.codeborne.selenide.*;
import helpers.InitDriver;
import io.qameta.allure.Step;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class StepFormsFilterLS extends InitDriver {

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
    public void BaseMenu(int sectionNumber) {
        BaseMenuPir menuPage = new BaseMenuPir();
        menuPage.dashboard.shouldBe(visible);
        menuPage.openSection(sectionNumber);
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

}
