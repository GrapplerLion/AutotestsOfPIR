package SelectorOfSortColumns;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SortingColumn {

    public SelenideElement inputSortLS = $("[data-test-id='pir_filter_sort_accNumber']");
    public SelenideElement inputSortAP = $("[data-test-id='pir_filter_sort_accNumber']");
    public SelenideElement inputSortDR = $("[data-test-id='pir_filter_sort_docName']");
    public SelenideElement inputSortSP = $("[data-test-id='pir_filter_sort_courtCaseNumber']");
    public SelenideElement inputSortIP = $("[data-test-id='pir_filter_sort_execProcNumber']");
    public ElementsCollection containsOfValueColumns = $("div[class='pir-table'] tbody").$$("tr");




}
