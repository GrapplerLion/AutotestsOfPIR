package FilterAndSortColumns;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ColumnSorting {

    public SelenideElement inputSortLS = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div > div > div.pir-table__row > div.pir-table__wrapper > div.el-auto-resizer > div > div.el-table__inner-wrapper > div.el-table__header-wrapper > table > thead > tr > th.el-table_1_column_3.is-leaf.pir-table__cell-header.el-table__cell > div > div > span.pir-table__cell_tool.active");

    public ElementsCollection containsOfValueColumns3 = $$("div[class='pir-table'] tbody tr.el-table_1_column_3");

}
