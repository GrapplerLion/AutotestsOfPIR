package Selectors.PlaningAP;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PlaningAPSelectors {

    public SelenideElement chekbox = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div > div > div.pir-table__row > div.pir-table__wrapper > div.el-auto-resizer > div > div.el-table__inner-wrapper > div.el-table__body-wrapper > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > table > tbody > tr.el-table__row.current-row > td.el-table_1_column_1.el-table-column--selection.pir-table__cell.el-table__cell > div > label > span > span");
    public SelenideElement openPlaning = $("#el-id-3270-8 > button");
    public void setTypePlaning(int typeSection){
        SelenideElement typePlaning = $("#el-id-3270-9 > li:nth-child("+ typeSection +")");
        typePlaning.click();
    }
    public SelenideElement comment = $("#el-id-3832-88");
    public SelenideElement sample = $("#el-id-3832-89");
    public void setTypeSample(int typeSection){
        SelenideElement typeSample = $("#el-id-3832-87 > div > div > div.el-select-dropdown__wrap.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > ul > li:nth-child("+ typeSection +")");
        typeSample.click();
    }
    public SelenideElement button = $("body > div.el-overlay > div > div > footer > button.el-button.el-button--primary");


}
