package Selectors.VerticalBarOptions;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SettingTableSelectors {

    public SelenideElement settingColumn = $("div[class$='pir-table__bar'] ul li:nth-of-type(1) > button");
    public SelenideElement settingColumnTable = $("div[class='table-bar__view']");

}
