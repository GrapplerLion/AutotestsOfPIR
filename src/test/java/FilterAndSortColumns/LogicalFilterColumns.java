package FilterAndSortColumns;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LogicalFilterColumns {

    public SelenideElement inputFilter = $("[data-test-id='pir_filter_action_open_isMeter']");
    public SelenideElement checkBox1 = $("#el-id-4472-35 > div > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > div > div.pir-filter__item.pir-filter__result > label:nth-child(2) > span.el-checkbox__input.is-checked > span");
    public SelenideElement checkBox2 = $("#el-id-4472-35 > div > div > div.el-scrollbar__wrap.el-scrollbar__wrap--hidden-default > div > div > div.pir-filter__item.pir-filter__result > label:nth-child(3) > span.el-checkbox__input.is-checked > span");
    public SelenideElement applyButton = $("[data-test-id='pir_filter_action_apply_isMeter']");
    public ElementsCollection listOfColumns = $$("div[class='pir-table'] tbody tr.el-table_1_column_14");
    public SelenideElement closeFilter = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div > div > div.pir-table__top > div > span:nth-child(3) > i");


}

