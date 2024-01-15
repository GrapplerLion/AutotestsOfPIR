package FilterAndSortColumns;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FilterColumnsPage {
    public SelenideElement filterIcon = $("table > thead > tr > th.el-table_1_column_2.is-leaf.pir-table__cell-header.el-table__cell > div > span:nth-of-type(3) > i");
    public SelenideElement valueFilter = $("#tab-params");
    public SelenideElement inputTypeFilter = $("div[data-test-id='pir_filter_condition_comparison_refDepartment_name_0']");


    public SelenideElement filter1 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Пусто"));
    public SelenideElement filter2 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Не пусто"));
    public SelenideElement filter3 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Равно"));
    public SelenideElement filter4 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Не равно"));
    public SelenideElement filter5 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Начинается с"));
    public SelenideElement filter6 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Не начинается с"));
    public SelenideElement filter7 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Заканчивается на"));
    public SelenideElement filter8 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Не заканчивается на"));
    public SelenideElement filter9 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Содержит"));
    public SelenideElement filter10 = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Не содержит"));


    public SelenideElement applyButton = $("button[data-test-id='pir_filter_action_apply_refDepartment_name']").$(byText("Применить"));
    public ElementsCollection valueField = $$("#pane-params > div.pir-filter__item.pir-filter__result > label > span.el-checkbox__label");
    public SelenideElement activeFilters = $("#app div.pir-table__top > div > span.el-tag.is-closable.el-tag--info.el-tag--light.pir-active-filter__tag");
    public SelenideElement closeFilters = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div > div > div.pir-table__top > div > span.el-tag.is-closable.el-tag--info.el-tag--light.pir-active-filter__tag > i");
    public ElementsCollection tableOfSize = $("div[class='pir-table'] tbody").$$("tr");

    public ElementsCollection containsOfValueColumns2 = $$("div[class='pir-table'] tbody tr.el-table_1_column_2");


    public SelenideElement enterValue = $("input[data-test-id='pir_filter_condition_comparison_refDepartment_name_0']");
}

//нерегистрозависимая проверка элементов в коллекции
//        Pattern pattern = Pattern.compile(".*[аА]$");
//        for (int i = 1; i < filterColumnsPage.valueField.size(); i++) {
//            SelenideElement element = filterColumnsPage.valueField.get(i);
//            String text = element.getText();
//            if (!pattern.matcher(text).find()) {
//                System.out.println("Элемент с текстом '" + text + "' не заканчивается на 'а' или 'А'");
//                // Дополнительные действия при несоответствии условию, если необходимо
//            }
//        }