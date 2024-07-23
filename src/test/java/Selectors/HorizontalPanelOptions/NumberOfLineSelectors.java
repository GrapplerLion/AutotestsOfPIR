package Selectors.HorizontalPanelOptions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NumberOfLineSelectors {

    public SelenideElement inputLine = $("span.el-pagination__sizes .select-trigger.el-tooltip__trigger");
    //
    public SelenideElement firstLine = $("div[class='el-select-dropdown__wrap el-scrollbar__wrap el-scrollbar__wrap--hidden-default'] ul li:nth-child(1)");
    public SelenideElement secondLine = $("div[class='el-select-dropdown__wrap el-scrollbar__wrap el-scrollbar__wrap--hidden-default'] ul li:nth-child(2)");
    public SelenideElement thirdLine = $("div[class='el-select-dropdown__wrap el-scrollbar__wrap el-scrollbar__wrap--hidden-default'] ul li:nth-child(3)");
    public SelenideElement fourthLine = $("div[class='el-select-dropdown__wrap el-scrollbar__wrap el-scrollbar__wrap--hidden-default'] ul li:nth-child(4)");
    public ElementsCollection tableOfSize = $("div[class='pir-table'] tbody").$$("tr");
    //public SelenideElement containerOflunges = $("div[id*='el-popper-container']");
}
