package Selectors.SubTask;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SubTaskSelectors {

    public SelenideElement subTask = $("#app > div > div.default-layout__menu > nav > div.pir-menu__footer > li:nth-child(1) > button");
    public ElementsCollection tableTask = $$("#app > div > div.default-layout__content > div.pir-tabs__content > div > div > div > div.pir-table__row > div.pir-table__wrapper > div.el-auto-resizer > div > div.el-table__inner-wrapper > div.el-table__body-wrapper > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > table > tbody");
    public SelenideElement tableSearch = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div > div > div.pir-table__row > div.pir-table__wrapper > div.el-auto-resizer > div > div.el-table__inner-wrapper > div.el-table__body-wrapper > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > table > tbody > tr.el-table__row.current-row > td.el-table_9_column_76.el-table-fixed-column--right.is-first-column.pir-table__cell.el-table__cell > div > span > button");
    public SelenideElement result = $("#el-id-4346-300 > div > div.el-alert.el-alert--info.is-light.pir-batch-result__message");
    public SelenideElement closeResult = $("body > div.el-overlay > div > div > footer > button");

}
