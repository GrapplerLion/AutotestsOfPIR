package tests.steps;

import Selectors.HorizontalPanelOptions.NumberOfLineSelectors;
import Selectors.Menu.BaseMenuPirSelectors;
import Selectors.RegPage.RegistrationPageSelectors;
import Selectors.VerticalBarOptions.SettingTableSelectors;
import Selectors.HorizontalPanelOptions.UpdateTableSelectors;
import Selectors.VerticalBarOptions.VerticalBarSelectors;
import Selectors.VerticalBarOptions.XlsFileExportSelectors;
import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class StepFormsFilterLS {

    @Step("Авторизация на сайт")
    public void Authorization(String username, String password) {
        RegistrationPageSelectors registrationPageSelectors = new RegistrationPageSelectors();
        registrationPageSelectors.openPage();
        registrationPageSelectors.pirBackground.shouldHave(text("PIR_NN"));
        registrationPageSelectors.pirWindowReg.shouldHave(text("Sign in to your account"));
        registrationPageSelectors.setFirstName(username);
        registrationPageSelectors.setLastName(password);
        registrationPageSelectors.setInputLogin();
    }

    @Step("Переход в раздел 'Лицевые счета'")
    public void BaseMenu(int sectionNumber) {
        BaseMenuPirSelectors menuPage = new BaseMenuPirSelectors();
        menuPage.dashboard.shouldBe(visible);
        menuPage.openSection(sectionNumber);
    }

    @Step("Настройка колонок")
    public void SettingTable() {
        SettingTableSelectors settingTableSelectors = new SettingTableSelectors();
        settingTableSelectors.settingColumn.click();
        settingTableSelectors.settingColumnTable.shouldBe(visible);
    }

    @Step("Вертикальный просмотр колонок")
    public void VerticalBar() {
        VerticalBarSelectors verticalBarSelectors = new VerticalBarSelectors();
        verticalBarSelectors.stendUpColumn.click();
        verticalBarSelectors.columnOfHeader.shouldBe(visible, enabled).shouldHave(text("Информация о записи"));
        verticalBarSelectors.allColumnsNumber.shouldHave(size(16));
        verticalBarSelectors.allColumnsName.shouldHave(texts("АС", "Подразделение", "Лицевой счет", "Тип абонента", "Состояние ЛС", "Состояние подключения", "Тип здания", "Мобильный телефон", "Стационарный телефон", "Email", "Адрес доставки", "ФИО", "Оборудование", "Наличие прибора учета (счетчика)", "Дата обновления данных по ЛС", "ФИО абонента"));
        verticalBarSelectors.stendUpColumn.shouldBe(visible);
    }

    @Step("Обновление таблицы")
    public void UpdateTable() {
        UpdateTableSelectors updateTableSelectors = new UpdateTableSelectors();
        updateTableSelectors.getNumberOfAccounts();
        updateTableSelectors.updateTableIcon.click();
        updateTableSelectors.getNumberOfAccounts();
        System.out.println(updateTableSelectors.getNumberOfAccounts());
    }

    @Step("Экспорт файла excel раздела - Лицевые счета")
    public void ExportFileAllTable() throws AWTException {
        XlsFileExportSelectors xlsFileExportSelectors = new XlsFileExportSelectors();
        xlsFileExportSelectors.filterIconForTypeHouse.click();
        xlsFileExportSelectors.inputSearchValue.setValue("Частный дом");
        xlsFileExportSelectors.inputButton.click();
        xlsFileExportSelectors.tableOfSize.forEach(element ->
                element.shouldHave(text("Частный дом")));
        xlsFileExportSelectors.buttonOfExport.click();
        sleep(5000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @Step("Количество строк на странице")
    public void QuantityOfStrings() {
        NumberOfLineSelectors numberOfLineSelectors = new NumberOfLineSelectors();
        numberOfLineSelectors.inputLine.shouldBe(visible, enabled).click();
        numberOfLineSelectors.firstLine.shouldBe(visible, enabled).click();
        numberOfLineSelectors.tableOfSize.shouldHave(CollectionCondition.size(20));
        numberOfLineSelectors.inputLine.shouldBe(visible, enabled).click();
        numberOfLineSelectors.secondLine.shouldBe(visible, enabled).click();
        numberOfLineSelectors.tableOfSize.shouldHave(CollectionCondition.size(100));
        numberOfLineSelectors.inputLine.shouldBe(visible, enabled).click();
        numberOfLineSelectors.thirdLine.shouldBe(visible, enabled).click();
        numberOfLineSelectors.tableOfSize.shouldHave(CollectionCondition.size(200));
        numberOfLineSelectors.inputLine.shouldBe(visible, enabled).click();
        numberOfLineSelectors.fourthLine.shouldBe(visible, enabled).click();
        numberOfLineSelectors.tableOfSize.shouldHave(CollectionCondition.size(400));
    }

}
