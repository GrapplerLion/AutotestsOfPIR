package FilterAndSortColumns;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StringFilterColumns {

    //кнопка иконки функции фильтра на колонку "Подразделение"
    public SelenideElement filterIconForDepartament = $("[data-test-id=pir_filter_action_open_refDepartment_name]");

    //выбор типа фильтра колонки "Подразделение"
    public SelenideElement typeFilterDepartament = $("[data-test-id='pir_filter_condition_comparison_refDepartment_name_0']");

    //кнопка иконки функции фильтра на колонку "Тип здания"
    public SelenideElement filterIconBuildType = $("[data-test-id=pir_filter_action_open_refBuildType_name]");

    //выбор типа фильтра колонки "Тип здания"
    public SelenideElement typeFilterBuildType = $("[data-test-id=pir_filter_condition_comparison_refBuildType_name_0]");


    public SelenideElement filter1 = $("[aria-hidden='false'] ul li:nth-child(1)");
    public SelenideElement filter2 = $("[aria-hidden='false'] ul li:nth-child(2)");
    public SelenideElement filter3 = $("[aria-hidden='false'] ul li:nth-child(3)");
    public SelenideElement filter4 = $("[aria-hidden='false'] ul li:nth-child(4)");
    public SelenideElement filter5 = $("[aria-hidden='false'] ul li:nth-child(5)");
    public SelenideElement filter6 = $("[aria-hidden='false'] ul li:nth-child(6)");
    public SelenideElement filter7 = $("[aria-hidden='false'] ul li:nth-child(7)");
    public SelenideElement filter8 = $("[aria-hidden='false'] ul li:nth-child(8)");
    public SelenideElement filter9 = $("[aria-hidden='false'] ul li:nth-child(9)");
    public SelenideElement filter10 = $("[aria-hidden='false'] ul li:nth-child(10)");
    public SelenideElement filter11 = $("[aria-hidden='false'] ul li:nth-child(11)");
    public SelenideElement filter12 = $("[aria-hidden='false'] ul li:nth-child(12)");


    //кнопка "применить" для фильтра колонки "Подразделение"
    public SelenideElement applyButtonForFilterDepartament = $("[data-test-id=pir_filter_action_apply_refDepartment_name]");

    //кнопка "применить" для фильтра колонки "Тип здания"
    public SelenideElement applyButtonForFilterBuildType = $("[data-test-id=pir_filter_action_apply_refBuildType_name]");


    //лист значений в попапе колонки "Подразделение"
    public ElementsCollection valueOfListDepartament = $$("[data-test-id=pir_filter_entries_list_refDepartment_name]");
    public ElementsCollection valueOfListBuildType = $$("[data-test-id=pir_filter_entries_list_refBuildType_name]");
    public SelenideElement valueOfMassFilter = $("[data-test-id='pir_filter_mass_input_refDepartment_name']");

    public SelenideElement activeFilters = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div > div > div.pir-table__top > div");
    public SelenideElement closeFilters = $("#app > div > div.default-layout__content > div.pir-tabs__content > div > div > div > div.pir-table__top > div > span.el-tag.is-closable.el-tag--info.el-tag--light.pir-active-filter__tag > i");
    public ElementsCollection tableOfSize = $("div[class='pir-table'] tbody").$$("tr");

    public ElementsCollection containsOfValueColumns7 = $$("div[class='pir-table'] tbody tr.el-table_1_column_7");
    public ElementsCollection containsOfValueColumns2 = $$("div[class='pir-table'] tbody tr.el-table_1_column_2");


    //Ввод значения в окне фильтра колонки "Подразделение"
    public SelenideElement enterValueForFilterDepartament = $("input[data-test-id='pir_filter_condition_comparison_refDepartment_name_0']");
    public SelenideElement enterValueForFilterBuildType = $("input[data-test-id=pir_filter_condition_comparison_refBuildType_name_0]");
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