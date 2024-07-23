package tests.PersonalAccount;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.steps.StepFormsFilterLS;

import java.awt.*;

public class PersonalAccountTestCase {

    @BeforeAll
    public static void SetUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "2560x1440");
        Configuration.timeout = 10000;
    }
    private final StepFormsFilterLS stepFormsFilterLS = new StepFormsFilterLS();


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






}
