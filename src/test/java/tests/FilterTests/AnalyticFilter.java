package tests.FilterTests;

import com.codeborne.selenide.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Arrays.stream;
import static tests.FilterTests.DateUtils.ComparisonType.*;


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
        public String checkBoxListSelector;
        public String type;
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
        public ElementsCollection checkBoxList;
        public String type;


        public Column(DataColumn column){
            value1 = column.value1;
            value2 = column.value2;
            value3 = column.value3;
            value4 = column.value4;
            value5 = column.value5;
            label = column.label;
            type = column.type;

            filterIcon = getElement(column.filterIconSelector);
            typeFilter = getElement(column.typeFilterSelector);
            applyButton = getElement(column.applyButtonSelector);
            valueOfList = getElements(column.valueOfListSelector);
            valueOfMassFilter = getElement(column.valueOfMassFilterSelector);
            containsOfValueColumns = $$(column.containsOfValueColumnsSelector);
            enterValue = getElement(column.enterValueSelector);
            activeFilters = getElement(column.activeFiltersSelector);
            closeFilters = getElement(column.closeFiltersSelector);
            tableOfSize = getElements(column.tableOfSizeSelector);
            checkBoxList = getElements(column.checkBoxListSelector);
        }

        private SelenideElement getElement(String selector){
            return selector !=null ? $(selector) : null;
        }
        private ElementsCollection getElements(String selector){
            return selector !=null ? $$(selector) : null;
        }

    }

class ColumnFilter {


    public Column column;

    public ColumnFilter(Column schema) {
        this.column = schema;
    }


    public void applyFilterAndCheckVoid(int sectionNumber) throws InterruptedException {
        scrollToElementIfNotVisible(column.filterIcon);
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfList.shouldHave(size(1));
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        column.tableOfSize.shouldHave(CollectionCondition.empty);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldBe(visible, enabled).click();
    }

    public void applyFilterAndCheckNotVoid(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfList.shouldHave(size(1));
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.tableOfSize.shouldHave(CollectionCondition.sizeGreaterThan(0));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterAndCheckEquals(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofMinutes(1)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value1, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldHave(text(column.value1)));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterAndCheckNotEquals(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value1, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldNotHave(text(column.value1)));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterAndCheckBeginWith(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value2);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value2, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldHave(matchText("'" + column.value2 + "'.*")));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterAndCheckNotBeginWith(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value2);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value2, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(visible, enabled).shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldNotHave(matchText("'" + column.value2 + "'.*")));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterAndCheckEndsWith(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value3);
        String condition = ".*" + column.value3;
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, condition, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText();
            if (!text.endsWith(column.value3)) {
                System.out.println("Элемент с текстом '" + text + "' не заканчивается на '" + column.value3 + "'");
            }
        }
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }

    public void applyFilterAndCheckNotEndsWith(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value3);
        String condition = ".*" + column.value3;
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, condition, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText();
            if (text.endsWith(column.value3)) {
                System.out.println("Элемент с текстом '" + text + "' заканчивается на '" + column.value3 + "'");
            }
        }
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }

    public void applyFilterAndCheckContains(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value4);
        String condition = ".*" + column.value4 + ".*";
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, condition, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (!text.contains(column.value4)) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит '" + column.value4 + "'");
            }
        }
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }

    public void applyFilterAndCheckNotContains(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value4);
        String condition = ".*" + column.value4 + ".*";
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, condition, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (text.contains(column.value4)) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит '" + column.value4 + "'");
            }
        }
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }

    public void applyFilterAndCheckIncludes(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfMassFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.valueOfMassFilter.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value5);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (!text.contains(column.value5)) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит '" + column.value5 + "'");
            }
        }
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }

    public void applyFilterAndCheckExcludes(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfMassFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.valueOfMassFilter.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value5);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (text.contains(column.value5)) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит '" + column.value5 + "'");
            }
        }
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }


    public void applyFilterBooleanVoid() throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        scrollToElementIfNotVisible(column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(3)).click();
        column.checkBoxList.find(text(column.value1)).click();
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(3)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        column.containsOfValueColumns.shouldHave(CollectionCondition.empty);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }

    public void applyFilterBooleanFalse() throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(3)).click();
        column.checkBoxList.find(text(column.value2)).click();
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(3)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldHave(text(column.value3)));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }

    public void applyFilterBooleanTrue() throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(3)).click();
        column.checkBoxList.find(text(column.value3)).click();
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(3)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldHave(text(column.value2)));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }


    public void applyFilterFloatVoid(int sectionNumber) throws InterruptedException {
        scrollToElementIfNotVisible(column.filterIcon);
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfList.shouldHave(size(1));
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        column.tableOfSize.shouldHave(CollectionCondition.empty);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldBe(visible, enabled).click();
    }

    public void applyFilterFloatNotVoid(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfList.shouldHave(size(1));
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.tableOfSize.shouldHave(CollectionCondition.sizeGreaterThan(0));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterFloatEquals(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value1, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldHave(text(column.value1)));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterFloatNotEquals(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value1, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldNotHave(text(column.value1)));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterFloatMore(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection listPopup = column.valueOfList;
        DateUtils.verifyFloat(listPopup, Float.parseFloat(column.value1),  MORE);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        ElementsCollection elements = column.containsOfValueColumns;
        DateUtils.verifyFloat(elements, Float.parseFloat(column.value1),  MORE);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterFloatMoreOrEqual(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection listPopup = column.valueOfList;
        DateUtils.verifyFloat(listPopup, Float.parseFloat(column.value1),  MORE_OR_EQUAL);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        ElementsCollection elements = column.containsOfValueColumns;
        DateUtils.verifyFloat(elements, Float.parseFloat(column.value1),  MORE_OR_EQUAL);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterFloatLess(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection listPopup = column.valueOfList;
        DateUtils.verifyFloat(listPopup, Float.parseFloat(column.value1),  LESS);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        ElementsCollection elements = column.containsOfValueColumns;
        DateUtils.verifyFloat(elements, Float.parseFloat(column.value1),  LESS);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterFloatLessOrEqual(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection listPopup = column.valueOfList;
        DateUtils.verifyFloat(listPopup, Float.parseFloat(column.value1),  DateUtils.ComparisonType.LESS_OR_EQUAL);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        ElementsCollection elements = column.containsOfValueColumns;
        DateUtils.verifyFloat(elements, Float.parseFloat(column.value1),  DateUtils.ComparisonType.LESS_OR_EQUAL);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }
    public void applyFilterAndCheckIncludesFloat(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfMassFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.valueOfMassFilter.shouldBe(enabled, Duration.ofSeconds(5)).setValue(String.valueOf(Float.parseFloat(column.value2)));
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (!text.contains(String.valueOf(Float.parseFloat(column.value2)))) {
                System.out.println("Элемент с текстом '" + element.getText() + "' не содержит '" + column.value5 + "'");
            }
        }
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }

    public void applyFilterAndCheckExcludesFloat(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfMassFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.valueOfMassFilter.shouldBe(enabled, Duration.ofSeconds(5)).setValue(String.valueOf(Float.parseFloat(column.value2)));
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        for (SelenideElement element : column.containsOfValueColumns) {
            String text = element.getText().toLowerCase();
            if (text.contains(String.valueOf(Float.parseFloat(column.value2)))) {
                System.out.println("Элемент с текстом '" + element.getText() + "' содержит '" + column.value5 + "'");
            }
        }
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(3)).click();
    }


    public void applyFilterDateVoid(int sectionNumber) throws InterruptedException {
        scrollToElementIfNotVisible(column.filterIcon);
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfList.shouldHave(size(1));
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        column.tableOfSize.shouldHave(CollectionCondition.empty);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldBe(visible, enabled).click();
    }

    public void applyFilterDateNotVoid(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.valueOfList.shouldHave(size(1));
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.tableOfSize.shouldHave(CollectionCondition.sizeGreaterThan(0));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterDateEquals(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value1, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldHave(text(column.value1)));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterDateNotEquals(int sectionNumber, boolean shouldMatch) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1);
        ElementsCollection elements = column.valueOfList;
        FilterUtils.checkElementsMatchCondition(elements, column.value1, shouldMatch);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldHave(text(column.label));
        column.containsOfValueColumns.forEach(element ->
                element.shouldNotHave(text(column.value1)));
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterDateMore(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection listPopup = column.valueOfList;
        DateUtils.verifyDates(listPopup, column.value1,  MORE);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        ElementsCollection elements = column.containsOfValueColumns;
        DateUtils.verifyDates(elements, column.value1,  MORE);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterDateMoreOrEqual(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection listPopup = column.valueOfList;
        DateUtils.verifyDates(listPopup, column.value1,  MORE_OR_EQUAL);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        ElementsCollection elements = column.containsOfValueColumns;
        DateUtils.verifyDates(elements, column.value1,  MORE_OR_EQUAL);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterDateLess(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);
        column.enterValue.shouldBe(visible, enabled);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection listPopup = column.valueOfList;
        DateUtils.verifyDates(listPopup, column.value1,  LESS);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        ElementsCollection elements = column.containsOfValueColumns;
        DateUtils.verifyDates(elements, column.value1,  LESS);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    public void applyFilterDateLessOrEqual(int sectionNumber) throws InterruptedException {
        DateUtils.shouldBeVisible(10, column.filterIcon);
        column.filterIcon.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.typeFilter.shouldBe(enabled, Duration.ofSeconds(5)).click();
        openFilter(sectionNumber);

        DateUtils.shouldBeVisible(10, column.enterValue);
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.enterValue.shouldBe(enabled, Duration.ofSeconds(5)).setValue(column.value1).pressEnter();
        ElementsCollection listPopup = column.valueOfList;
        DateUtils.verifyDates(listPopup, column.value1,  DateUtils.ComparisonType.LESS_OR_EQUAL);
        column.applyButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
        column.activeFilters.shouldBe(enabled, Duration.ofSeconds(5)).shouldHave(text(column.label));
        ElementsCollection elements = column.containsOfValueColumns;
        DateUtils.verifyDates(elements, column.value1,  DateUtils.ComparisonType.LESS_OR_EQUAL);
        column.closeFilters.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }


    private void openFilter(int sectionNumber) {
        SelenideElement filterIsNumber = $("[aria-hidden='false'] ul li:nth-child(" + sectionNumber + ")");
        filterIsNumber.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }

    SelenideElement scroll = $(".el-table__inner-wrapper .el-scrollbar__bar.is-horizontal");

    public void scrollToElementIfNotVisible(SelenideElement element) {
        if (element.exists() && !element.isDisplayed()) {
            if (scroll.exists() && !scroll.isDisplayed()) {
                $(".el-table__inner-wrapper").hover().click();

                int maxScrolls = 10;
                int attempts = 0;

                do{
                    actions().sendKeys(Keys.ARROW_RIGHT).perform();
                    attempts++;
                }while (!element.isDisplayed() || attempts < maxScrolls);
            }
        }
    }
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

    void testColumnFiltersLS() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream jsonArray = getClass().getResourceAsStream("/JsonFormsColumnsLS.json")) {
            DataColumn[] dataColumns = mapper.readValue(jsonArray, DataColumn[].class);
            List<DataColumn> sections = new ArrayList<>(Arrays.asList(dataColumns));

            for (DataColumn dataColumn : sections) {
                if (dataColumn != null) {
                    if (dataColumn.type.equals("Boolean")) {
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        // Логическая колонка
                        columnFilter.applyFilterBooleanVoid();
                        columnFilter.applyFilterBooleanFalse();
                        columnFilter.applyFilterBooleanTrue();
                    } else if (dataColumn.type.equals("String")) {
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
                    }else if (dataColumn.type.equals("Date")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterDateVoid(1);
                        columnFilter.applyFilterDateNotVoid(2);
                        columnFilter.applyFilterDateEquals(3, true);
                        columnFilter.applyFilterDateNotEquals(4, false);
                        columnFilter.applyFilterDateMore(5);
                        columnFilter.applyFilterDateMoreOrEqual(6);
                        columnFilter.applyFilterDateLess(7);
                        columnFilter.applyFilterDateLessOrEqual(8);
                    }else if(dataColumn.type.equals("Float")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterFloatVoid(1);
                        columnFilter.applyFilterFloatNotVoid(2);
                        columnFilter.applyFilterFloatEquals(3, true);
                        columnFilter.applyFilterFloatNotEquals(4, false);
                        columnFilter.applyFilterFloatMore(5);
                        columnFilter.applyFilterFloatMoreOrEqual(6);
                        columnFilter.applyFilterFloatLess(7);
                        columnFilter.applyFilterFloatLessOrEqual(8);
                        columnFilter.applyFilterAndCheckIncludesFloat(11);
                        columnFilter.applyFilterAndCheckExcludesFloat(12);
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void testColumnFiltersAP() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream jsonArray = getClass().getResourceAsStream("/JsonFormsColumnsAP.json")) {
            DataColumn[] dataColumns = mapper.readValue(jsonArray, DataColumn[].class);
            List<DataColumn> sections = new ArrayList<>(Arrays.asList(dataColumns));

            for (DataColumn dataColumn : sections) {
                if (dataColumn != null) {
                    if (dataColumn.type.equals("Boolean")) {
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        // Логическая колонка
                        columnFilter.applyFilterBooleanVoid();
                        columnFilter.applyFilterBooleanFalse();
                        columnFilter.applyFilterBooleanTrue();
                    } else if (dataColumn.type.equals("String")) {
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
                    }else if (dataColumn.type.equals("Date")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterDateVoid(1);
                        columnFilter.applyFilterDateNotVoid(2);
                        columnFilter.applyFilterDateEquals(3, true);
                        columnFilter.applyFilterDateNotEquals(4, false);
                        columnFilter.applyFilterDateMore(5);
                        columnFilter.applyFilterDateMoreOrEqual(6);
                        columnFilter.applyFilterDateLess(7);
                        columnFilter.applyFilterDateLessOrEqual(8);
                    }else if(dataColumn.type.equals("Float")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterFloatVoid(1);
                        columnFilter.applyFilterFloatNotVoid(2);
                        columnFilter.applyFilterFloatEquals(3, true);
                        columnFilter.applyFilterFloatNotEquals(4, false);
                        columnFilter.applyFilterFloatMore(5);
                        columnFilter.applyFilterFloatMoreOrEqual(6);
                        columnFilter.applyFilterFloatLess(7);
                        columnFilter.applyFilterFloatLessOrEqual(8);
                        columnFilter.applyFilterAndCheckIncludesFloat(11);
                        columnFilter.applyFilterAndCheckExcludesFloat(12);
                    }
                }
            }
        }
    }

    void testColumnFiltersDR() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream jsonArray = getClass().getResourceAsStream("/JsonFormsColumnsDR.json")) {
            DataColumn[] dataColumns = mapper.readValue(jsonArray, DataColumn[].class);
            List<DataColumn> sections = new ArrayList<>(Arrays.asList(dataColumns));

            for (DataColumn dataColumn : sections) {
                if (dataColumn != null) {
                    if (dataColumn.type.equals("Boolean")) {
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        // Логическая колонка
                        columnFilter.applyFilterBooleanVoid();
                        columnFilter.applyFilterBooleanFalse();
                        columnFilter.applyFilterBooleanTrue();
                    } else if (dataColumn.type.equals("String")) {
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
                    }else if (dataColumn.type.equals("Date")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterDateVoid(1);
                        columnFilter.applyFilterDateNotVoid(2);
                        columnFilter.applyFilterDateEquals(3, true);
                        columnFilter.applyFilterDateNotEquals(4, false);
                        columnFilter.applyFilterDateMore(5);
                        columnFilter.applyFilterDateMoreOrEqual(6);
                        columnFilter.applyFilterDateLess(7);
                        columnFilter.applyFilterDateLessOrEqual(8);
                    }else if(dataColumn.type.equals("Float")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterFloatVoid(1);
                        columnFilter.applyFilterFloatNotVoid(2);
                        columnFilter.applyFilterFloatEquals(3, true);
                        columnFilter.applyFilterFloatNotEquals(4, false);
                        columnFilter.applyFilterFloatMore(5);
                        columnFilter.applyFilterFloatMoreOrEqual(6);
                        columnFilter.applyFilterFloatLess(7);
                        columnFilter.applyFilterFloatLessOrEqual(8);
                        columnFilter.applyFilterAndCheckIncludesFloat(11);
                        columnFilter.applyFilterAndCheckExcludesFloat(12);
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void testColumnFiltersSP() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream jsonArray = getClass().getResourceAsStream("/JsonFormsColumnsSP.json")) {
            DataColumn[] dataColumns = mapper.readValue(jsonArray, DataColumn[].class);
            List<DataColumn> sections = new ArrayList<>(Arrays.asList(dataColumns));

            for (DataColumn dataColumn : sections) {
                if (dataColumn != null) {
                    if (dataColumn.type.equals("Boolean")) {
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        // Логическая колонка
                        columnFilter.applyFilterBooleanVoid();
                        columnFilter.applyFilterBooleanFalse();
                        columnFilter.applyFilterBooleanTrue();
                    } else if (dataColumn.type.equals("String")) {
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
                    }else if (dataColumn.type.equals("Date")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterDateVoid(1);
                        columnFilter.applyFilterDateNotVoid(2);
                        columnFilter.applyFilterDateEquals(3, true);
                        columnFilter.applyFilterDateNotEquals(4, false);
                        columnFilter.applyFilterDateMore(5);
                        columnFilter.applyFilterDateMoreOrEqual(6);
                        columnFilter.applyFilterDateLess(7);
                        columnFilter.applyFilterDateLessOrEqual(8);
                    }else if(dataColumn.type.equals("Float")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterFloatVoid(1);
                        columnFilter.applyFilterFloatNotVoid(2);
                        columnFilter.applyFilterFloatEquals(3, true);
                        columnFilter.applyFilterFloatNotEquals(4, false);
                        columnFilter.applyFilterFloatMore(5);
                        columnFilter.applyFilterFloatMoreOrEqual(6);
                        columnFilter.applyFilterFloatLess(7);
                        columnFilter.applyFilterFloatLessOrEqual(8);
                        columnFilter.applyFilterAndCheckIncludesFloat(11);
                        columnFilter.applyFilterAndCheckExcludesFloat(12);
                    }
                }
            }
        }
    }

    void testColumnFiltersIP() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream jsonArray = getClass().getResourceAsStream("/JsonFormsColumnsIP.json")) {
            DataColumn[] dataColumns = mapper.readValue(jsonArray, DataColumn[].class);
            List<DataColumn> sections = new ArrayList<>(Arrays.asList(dataColumns));

            for (DataColumn dataColumn : sections) {
                if (dataColumn != null) {
                    if (dataColumn.type.equals("Boolean")) {
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        // Логическая колонка
                        columnFilter.applyFilterBooleanVoid();
                        columnFilter.applyFilterBooleanFalse();
                        columnFilter.applyFilterBooleanTrue();
                    } else if (dataColumn.type.equals("String")) {
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
                    }else if (dataColumn.type.equals("Date")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterDateVoid(1);
                        columnFilter.applyFilterDateNotVoid(2);
                        columnFilter.applyFilterDateEquals(3, true);
                        columnFilter.applyFilterDateNotEquals(4, false);
                        columnFilter.applyFilterDateMore(5);
                        columnFilter.applyFilterDateMoreOrEqual(6);
                        columnFilter.applyFilterDateLess(7);
                        columnFilter.applyFilterDateLessOrEqual(8);
                    }else if(dataColumn.type.equals("Float")){
                        Column column = new Column(dataColumn);
                        ColumnFilter columnFilter = new ColumnFilter(column);
                        columnFilter.applyFilterFloatVoid(1);
                        columnFilter.applyFilterFloatNotVoid(2);
                        columnFilter.applyFilterFloatEquals(3, true);
                        columnFilter.applyFilterFloatNotEquals(4, false);
                        columnFilter.applyFilterFloatMore(5);
                        columnFilter.applyFilterFloatMoreOrEqual(6);
                        columnFilter.applyFilterFloatLess(7);
                        columnFilter.applyFilterFloatLessOrEqual(8);
                        columnFilter.applyFilterAndCheckIncludesFloat(11);
                        columnFilter.applyFilterAndCheckExcludesFloat(12);
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



class DateUtils {
    public enum ComparisonType {
        MORE,
        MORE_OR_EQUAL,
        LESS,
        LESS_OR_EQUAL
    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void verifyDates(ElementsCollection elements, String date, ComparisonType comparisonType) {
        LocalDate filterDate = LocalDate.parse(date, formatter);

        for (SelenideElement element : elements) {
            LocalDate elementDate = LocalDate.parse(element.getText(), formatter);

            switch (comparisonType) {
                case MORE:
                    if (!elementDate.isAfter(filterDate)) {
                        throw new AssertionError("Date " + elementDate + " is not after " + filterDate);
                    }
                    break;
                case MORE_OR_EQUAL:
                    if (!elementDate.isAfter(filterDate) && !elementDate.isEqual(filterDate)) {
                        throw new AssertionError("Date " + elementDate + " is not after or equal to " + filterDate);
                    }
                    break;
                case LESS:
                    if (!elementDate.isBefore(filterDate)) {
                        throw new AssertionError("Date " + elementDate + " is not before " + filterDate);
                    }
                    break;
                case LESS_OR_EQUAL:
                    if (!elementDate.isBefore(filterDate) && !elementDate.isEqual(filterDate)) {
                        throw new AssertionError("Date " + elementDate + " is not before or equal to " + filterDate);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown comparison type: " + comparisonType);
            }
        }

        System.out.println("All dates are " + comparisonType + " " + filterDate);
    }
    public static void shouldBeVisible(int times, SelenideElement element) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            Thread.sleep(1000);
            if (element.exists()) {
                return;
            }
        }
    }

    public static void verifyFloat(ElementsCollection elements, float referenceValue, ComparisonType comparisonType) {
        for (SelenideElement element : elements) {
            float elementValue = Float.parseFloat(element.getText());

            switch (comparisonType) {
                case MORE:
                    if (!(elementValue > referenceValue)) {
                        throw new AssertionError("Number " + elementValue + " is not greater than " + referenceValue);
                    }
                    break;
                case MORE_OR_EQUAL:
                    if (!(elementValue >= referenceValue)) {
                        throw new AssertionError("Number " + elementValue + " is not greater than or equal to " + referenceValue);
                    }
                    break;
                case LESS:
                    if (!(elementValue < referenceValue)) {
                        throw new AssertionError("Number " + elementValue + " is not less than " + referenceValue);
                    }
                    break;
                case LESS_OR_EQUAL:
                    if (!(elementValue <= referenceValue)) {
                        throw new AssertionError("Number " + elementValue + " is not less than or equal to " + referenceValue);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown comparison type: " + comparisonType);
            }
        }

        System.out.println("All numbers are " + comparisonType + " " + referenceValue);
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