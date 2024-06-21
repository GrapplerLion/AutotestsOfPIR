package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


class DataColumn {
        public String value1;
        public String value2;
        public String value3;
        public String value4;
        public String value5;
        public String label;
        public String filterIconSelector;
        public String typeFilterSelector;
        public String applyButtonSelector;
        public String valueOfListSelector;
        public String valueOfMassFilterSelector;
        public String containsOfValueColumnsSelector;
        public String enterValueSelector;
        public String activeFiltersSelector;
        public String closeFiltersSelector;
        public String tableOfSizeSelector;

    }

    class Column {

        public String value1;
        public String value2;
        public String value3;
        public String value4;
        public String value5;
        public String label;
        public SelenideElement filterIcon;
        public SelenideElement typeFilter;
        public SelenideElement applyButton;
        public ElementsCollection valueOfList;
        public SelenideElement valueOfMassFilter;
        public ElementsCollection containsOfValueColumns;
        public SelenideElement enterValue;
        public SelenideElement activeFilters;
        public SelenideElement closeFilters;
        public ElementsCollection tableOfSize;

        public Column(DataColumn column){
            value1 = column.value1;
            value2 = column.value2;
            value3 = column.value3;
            value4 = column.value4;
            value5 = column.value5;
            label = column.label;
            filterIcon = $(column.filterIconSelector);
            typeFilter = $(column.typeFilterSelector);
            applyButton = $(column.applyButtonSelector);
            valueOfList = $$(column.valueOfListSelector);
            valueOfMassFilter = $(column.valueOfMassFilterSelector);
            containsOfValueColumns = $$(column.containsOfValueColumnsSelector);
            enterValue = $(column.enterValueSelector);
            activeFilters = $(column.activeFiltersSelector);
            closeFilters = $(column.closeFiltersSelector);
            tableOfSize = $$(column.tableOfSizeSelector);
        }
    }

class ColumnFilter{

    public Column column;
    public ColumnFilter(Column schema){
        this.column = schema;
    }


    public void applyFilterAndCheckVoid(int sectionNumber) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.valueOfList.shouldHave(size(1));
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldHave(text(column.label));
        column.tableOfSize.shouldHave(CollectionCondition.empty);
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckNotVoid(int sectionNumber) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.valueOfList.shouldHave(size(1));
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldHave(text(column.label));
        column.tableOfSize.shouldHave(CollectionCondition.sizeGreaterThan(0));
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckEquals(int sectionNumber, boolean shouldMatch) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled).setValue(column.value1);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value1, shouldMatch);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldHave(text(column.value1)));
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckNotEquals(int sectionNumber, boolean shouldMatch) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled).setValue(column.value1);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value1, shouldMatch);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldNotHave(text(column.value1)));
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckBeginWith(int sectionNumber, boolean shouldMatch) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled).setValue(column.value2);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value2, shouldMatch);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldHave(matchText("'"+column.value2+"'.*")));
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckNotBeginWith(int sectionNumber, boolean shouldMatch) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled).setValue(column.value2);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value2, shouldMatch);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldNotHave(matchText("'"+column.value2+"'.*")));
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckEndsWith(int sectionNumber, boolean shouldMatch) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled).setValue(column.value3);
        String condition = ".*" + column.value3;
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, condition, shouldMatch);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText();
            if (!text.endsWith(column.value3)) {
                System.out.println("Элемент с текстом '" + text + "' не заканчивается на '"+column.value3+"'");
            }
        }
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckNotEndsWith(int sectionNumber, boolean shouldMatch) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled).setValue(column.value3);
        String condition = ".*" + column.value3;
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, condition, shouldMatch);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText();
            if (text.endsWith(column.value3)) {
                System.out.println("Элемент с текстом '" + text + "' заканчивается на '"+column.value3+"'");
            }
        }
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckContains(int sectionNumber, boolean shouldMatch) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled).setValue(column.value4);
        String condition = ".*" + column.value4 + ".*";
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, condition, shouldMatch);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (!text.contains(column.value4)) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит '"+column.value4+"'");
            }
        }
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckNotContains(int sectionNumber, boolean shouldMatch) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled).setValue(column.value4);
        String condition = ".*" + column.value4 + ".*";
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, condition, shouldMatch);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (text.contains(column.value4)) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит '"+column.value4+"'");
            }
        }
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckIncludes(int sectionNumber) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.valueOfMassFilter.shouldBe(visible, enabled).setValue(column.value5);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (!text.contains(column.value5)) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит '" + column.value5+ "'");
            }
        }
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckExcludes(int sectionNumber) {
        column.filterIcon.shouldBe(visible, enabled).click();
        column.typeFilter.shouldBe(visible, enabled).click();
        openFilter(sectionNumber);
        column.valueOfMassFilter.shouldBe(visible, enabled).setValue(column.value5);
        column.applyButton.shouldBe(visible, enabled).click();
        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (text.contains(column.value5)) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит '" + column.value5+ "'");
            }
        }
        column.closeFilters.shouldBe(visible, enabled).click();
    }

    //"АП Новострой \n ТУ Дербентского р-на"

//    private void applyFilter(int filterNumber, String value, boolean shouldMatch, boolean matchText, boolean startsWith, boolean endsWith, boolean contains) {
//        column.filterIcon.shouldBe(visible, enabled).click();
//        column.typeFilter.shouldBe(visible, enabled).click();
//        openFilter(filterNumber);
//
//        if (value != null) {
//            column.enterValue.setValue(value);
//        }
//
//        ElementsCollection elements = column.valueOfList;
//
//        if (matchText) {
//            FilterUtils.checkElementsMatchCondition(elements, value, shouldMatch);
//        } else if (startsWith) {
//            FilterUtils.checkElementsMatchCondition(elements, "^" + value, shouldMatch);
//        } else if (endsWith) {
//            FilterUtils.checkElementsMatchCondition(elements, value + "$", shouldMatch);
//        } else if (contains) {
//            FilterUtils.checkElementsMatchCondition(elements, ".*" + value + ".*", shouldMatch);
//        }
//
//        column.applyButton.shouldBe(visible, enabled).click();
//        column.activeFilters.shouldHave(text(column.label));
//
//        column.containsOfValueColumns.forEach(element -> {
//            if (matchText) {
//                element.shouldHave(text(value));
//            } else if (startsWith) {
//                element.shouldHave(matchText("^" + value));
//            } else if (endsWith) {
//                element.shouldHave(matchText(value + "$"));
//            } else if (contains) {
//                element.shouldHave(matchText(".*" + value + ".*"));
//            }
//        });
//
//        column.closeFilters.shouldBe(visible, enabled).click();
//    }

    private void openFilter(int sectionNumber) {
        SelenideElement filterIsNumber = $("[aria-hidden='false'] ul li:nth-child(" + sectionNumber + ")");
        filterIsNumber.click();
    }


//    public void applyFilterAndCheckVoid() {
//        applyFilter(1, null, false, false, false, false, false);
//    }
//
//    public void applyFilterAndCheckNotVoid() {
//        applyFilter(2, null, false, false, false, false, false);
//    }
//
//    public void applyFilterAndCheckEquals(String value, boolean shouldMatch) {
//        applyFilter(3, value, shouldMatch, true, false, false, false);
//    }
//
//    public void applyFilterAndCheckNotEquals(String value, boolean shouldMatch) {
//        applyFilter(4, value, !shouldMatch, true, false, false, false);
//    }
//
//    public void applyFilterAndCheckBeginWith(String value, boolean shouldMatch){
//        applyFilter(5, value, shouldMatch, false, true, false, false);
//    }
//
//    public void applyFilterAndCheckNotBeginWith(String value, boolean shouldMatch){
//        applyFilter(6, value, !shouldMatch, false, true, false, false);
//    }
//
//    public void applyFilterAndCheckEndsWith(String suffix, boolean shouldMatch) {
//        applyFilter(7, suffix, shouldMatch, false, false, true, false);
//    }
//
//    public void applyFilterAndCheckNotEndsWith(String suffix, boolean shouldMatch) {
//        applyFilter(8, suffix, !shouldMatch, false, false, true, false);
//    }
//
//    public void applyFilterAndCheckContains(String substring, boolean shouldMatch) {
//        applyFilter(9, substring, shouldMatch, false, false, false, true);
//    }
//
//    public void applyFilterAndCheckNotContains(String substring, boolean shouldMatch) {
//        applyFilter(10, substring, !shouldMatch, false, false, false, true);
//    }
//
//    public void applyFilterAndCheckMass(int sectionNumber, List<String> values, boolean shouldMatch) {
//        column.filterIcon.shouldBe(visible, enabled).click();
//        column.typeFilter.shouldBe(visible, enabled).click();
//        openFilter(sectionNumber);
//        for (String value : values) {
//            column.valueOfMassFilter.shouldBe(visible, enabled).setValue(value);
//        }
//        column.applyButton.shouldBe(visible, enabled).click();
//        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
//        column.containsOfValueColumns.forEach(element -> {
//            boolean elementMatches = values.stream().anyMatch(value -> element.getText().contains(value));
//            if (shouldMatch) {
//                if (!elementMatches) {
//                    element.shouldHave(text(values.get(0)));
//                }
//            } else {
//                if (elementMatches) {
//                    element.shouldNotHave(text(values.get(0)));
//                }
//            }
//        });
//        column.closeFilters.shouldBe(visible, enabled).click();
//    }


}
class Section {
    DataColumn[] columns;

    public Section(DataColumn column) {
        // Assuming some logic to populate columns array
        this.columns = new DataColumn[]{column};
    }
}

class Main {

    public Main() {

    }

    private static List<DataColumn> sections;

    void testColumnFilters() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream jsonArray = getClass().getResourceAsStream("/JsonFormsColumnsLS.json")) {
            DataColumn[] dataColumns = mapper.readValue(jsonArray, DataColumn[].class);
            sections = new ArrayList<>(Arrays.asList(dataColumns));
        }

        for (DataColumn dataColumn : sections) {
                        if (dataColumn != null) {
                            Column column = new Column(dataColumn);
                            ColumnFilter columnFilter = new ColumnFilter(column);
                            columnFilter.applyFilterAndCheckVoid(1);
                            columnFilter.applyFilterAndCheckNotVoid(2);
                            columnFilter.applyFilterAndCheckEquals(3, true);
                            columnFilter.applyFilterAndCheckNotEquals(4, false);
                            columnFilter.applyFilterAndCheckBeginWith(5, true);
                            columnFilter.applyFilterAndCheckNotBeginWith(6, false);
                            columnFilter.applyFilterAndCheckEndsWith(7, true);
                            columnFilter.applyFilterAndCheckNotEndsWith(8, false);
                            columnFilter.applyFilterAndCheckContains(9, true);
                            columnFilter.applyFilterAndCheckNotContains(10, false);
                            columnFilter.applyFilterAndCheckIncludes(11);
                            columnFilter.applyFilterAndCheckExcludes(12);
                        }
        }
    }
}



class FilterUtils {

        public static void checkElementsMatchCondition(ElementsCollection elements, String condition, boolean shouldMatch) {
            boolean value = true;
            for (int i = 1; i < elements.size(); i++) {
                if (shouldMatch) {
                    if (!elements.get(i).shouldHave(matchText(condition)).is(visible)) {
                        value = false;
                        break;
                    }
                } else {
                    if (!elements.get(i).shouldNotHave(matchText(condition)).is(visible)) {
                        value = false;
                        break;
                    }
                }
            }
            if (!value) {
                if (shouldMatch) {
                    System.out.println("Не все элементы, кроме первого, равняются '" + condition + "'");
                } else {
                    System.out.println("Не все элементы, кроме первого, не равняются '" + condition + "'");
                }
            }
        }

}

