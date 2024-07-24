package Selectors.SectionCard;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SectionCardSelectors {

    public SelenideElement sectionCard = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div.view__main > div.view__menu > nav > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > ul");
    public void setSectionCard(int sectionNumber){
        SelenideElement section = $("li:nth-child("+ sectionNumber +")");
        section.click();
    }
    public SelenideElement edit = $("#\\31  > div > div > div.pir-table__row > div.pir-table__wrapper > div > div > div.el-table__inner-wrapper > div.el-table__body-wrapper > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > table > tbody > tr.el-table__row.current-row > td.el-table_10_column_82.el-table-fixed-column--right.is-first-column.pir-table__cell.el-table__cell > div > span > button");

}
