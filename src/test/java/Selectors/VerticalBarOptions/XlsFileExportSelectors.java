package Selectors.VerticalBarOptions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class XlsFileExportSelectors {

    //путь к выгрузке файла через настройки фильтра в колонке "Абонент"
    public SelenideElement filterIconForTypeHouse = $("[data-test-id='pir_filter_action_open_refBuildType_name']");

    public SelenideElement inputSearchValue = $("[data-test-id='pir_filter_search_value_refBuildType_name']");


    public SelenideElement inputButton = $("[data-test-id='pir_filter_action_apply_refBuildType_name']");

    public ElementsCollection tableOfSize = $$("div[class='pir-table'] tbody tr.el-table_1_column_7");

    public SelenideElement buttonOfExport = $("div[class$='pir-table__bar'] ul li:nth-of-type(3) > button");

}

