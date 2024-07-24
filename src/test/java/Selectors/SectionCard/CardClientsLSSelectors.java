package Selectors.SectionCard;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardClientsLSSelectors {

    public SelenideElement table = $("div[class='pir-table'] tbody");
    public void setClients(int numberString){
        SelenideElement clients = table.$("tr:nth-child("+ numberString +")");
        clients.doubleClick();
    }
    public SelenideElement sectionCard = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div.view__main > div.view__menu > nav > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > ul");
    public void setSectionCard(int sectionNumber){
        SelenideElement section = sectionCard.$("li:nth-child("+ sectionNumber +")");
        section.click();
    }
    public SelenideElement edit = $("#\\31  > div > div > div.pir-table__row > div.pir-table__wrapper > div > div > div.el-table__inner-wrapper > div.el-table__body-wrapper > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > table > tbody > tr.el-table__row.current-row > td.el-table_10_column_82.el-table-fixed-column--right.is-first-column.pir-table__cell.el-table__cell > div > span > button");
    public SelenideElement status = $("#el-id-4581-131 > div > form > div.el-form-item.is-required.asterisk-left.pir-form__field > div > div > div > div > div");
    public SelenideElement selectStatus = $("#el-id-9229-144 > div > div > div.el-select-dropdown__wrap.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > ul");
    public void setSelectStatus(int numberStatus){
        SelenideElement statusType = selectStatus.$("li:nth-child("+ numberStatus +")");
        statusType.click();
    }
    public SelenideElement comment = $("#el-id-4581-147");
    public SelenideElement saveButton = $("body > div.el-overlay > div > div > footer > button.el-button.el-button--primary");
}
