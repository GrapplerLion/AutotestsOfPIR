package tests.steps;

import SelectorOfSortColumns.SortingColumn;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StepFormsSorting {

    private final SortingColumn sortingColumn = new SortingColumn();

    @Step("Сортировка колонок - Лицевой счёт")
    public void SortOfColumnsLS() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 12 и 13)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 11 || colIndex == 12) { // Пропуск 12 и 13 столбцов (индексы 11 и 12)
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            List<String> initialValues = new ArrayList<>(originalValues);
            Collections.sort(initialValues);
            assertEquals(initialValues, originalValues, "Значения в столбце " + (colIndex + 1) + " изначально должны быть отсортированы в порядке возрастания");

            // Выполняем клик для сортировки по убыванию
            sortingColumn.inputSortLS.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после сортировки по убыванию
            List<String> sortedDescValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedDescValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения отсортированы по убыванию
            List<String> expectedDescValues = new ArrayList<>(originalValues);
            Collections.sort(expectedDescValues, Collections.reverseOrder());
            assertEquals(expectedDescValues, sortedDescValues, "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

            // Выполняем клик для хаотичной сортировки
            sortingColumn.inputSortLS.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после хаотичной сортировки
            List<String> sortedRandomValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedRandomValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения не отсортированы ни по возрастанию, ни по убыванию
            assertNotEquals(originalValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны совпадать с исходными значениями после третьего клика");
            assertNotEquals(expectedDescValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке убывания после третьего клика");
//            Collections.sort(sortedRandomValues);
            assertNotEquals(initialValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке возрастания после третьего клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }
    public void SortOfColumnsAP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 12 и 13)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 11 || colIndex == 12) { // Пропуск 12 и 13 столбцов (индексы 11 и 12)
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            List<String> initialValues = new ArrayList<>(originalValues);
            Collections.sort(initialValues);
            assertEquals(initialValues, originalValues, "Значения в столбце " + (colIndex + 1) + " изначально должны быть отсортированы в порядке возрастания");

            // Выполняем клик для сортировки по убыванию
            sortingColumn.inputSortAP.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после сортировки по убыванию
            List<String> sortedDescValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedDescValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения отсортированы по убыванию
            List<String> expectedDescValues = new ArrayList<>(originalValues);
            Collections.sort(expectedDescValues, Collections.reverseOrder());
            assertEquals(expectedDescValues, sortedDescValues, "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

            // Выполняем клик для хаотичной сортировки
            sortingColumn.inputSortAP.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после хаотичной сортировки
            List<String> sortedRandomValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedRandomValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения не отсортированы ни по возрастанию, ни по убыванию
            assertNotEquals(originalValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны совпадать с исходными значениями после третьего клика");
            assertNotEquals(expectedDescValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке убывания после третьего клика");
//            Collections.sort(sortedRandomValues);
            assertNotEquals(initialValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке возрастания после третьего клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }
    public void SortOfColumnsDR() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 12 и 13)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 11 || colIndex == 12) { // Пропуск 12 и 13 столбцов (индексы 11 и 12)
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            List<String> initialValues = new ArrayList<>(originalValues);
            Collections.sort(initialValues);
            assertEquals(initialValues, originalValues, "Значения в столбце " + (colIndex + 1) + " изначально должны быть отсортированы в порядке возрастания");

            // Выполняем клик для сортировки по убыванию
            sortingColumn.inputSortDR.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после сортировки по убыванию
            List<String> sortedDescValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedDescValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения отсортированы по убыванию
            List<String> expectedDescValues = new ArrayList<>(originalValues);
            Collections.sort(expectedDescValues, Collections.reverseOrder());
            assertEquals(expectedDescValues, sortedDescValues, "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

            // Выполняем клик для хаотичной сортировки
            sortingColumn.inputSortDR.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после хаотичной сортировки
            List<String> sortedRandomValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedRandomValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения не отсортированы ни по возрастанию, ни по убыванию
            assertNotEquals(originalValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны совпадать с исходными значениями после третьего клика");
            assertNotEquals(expectedDescValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке убывания после третьего клика");
//            Collections.sort(sortedRandomValues);
            assertNotEquals(initialValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке возрастания после третьего клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }
    public void SortOfColumnsSP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 12 и 13)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 11 || colIndex == 12) { // Пропуск 12 и 13 столбцов (индексы 11 и 12)
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            List<String> initialValues = new ArrayList<>(originalValues);
            Collections.sort(initialValues);
            assertEquals(initialValues, originalValues, "Значения в столбце " + (colIndex + 1) + " изначально должны быть отсортированы в порядке возрастания");

            // Выполняем клик для сортировки по убыванию
            sortingColumn.inputSortSP.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после сортировки по убыванию
            List<String> sortedDescValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedDescValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения отсортированы по убыванию
            List<String> expectedDescValues = new ArrayList<>(originalValues);
            Collections.sort(expectedDescValues, Collections.reverseOrder());
            assertEquals(expectedDescValues, sortedDescValues, "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

            // Выполняем клик для хаотичной сортировки
            sortingColumn.inputSortSP.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после хаотичной сортировки
            List<String> sortedRandomValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedRandomValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения не отсортированы ни по возрастанию, ни по убыванию
            assertNotEquals(originalValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны совпадать с исходными значениями после третьего клика");
            assertNotEquals(expectedDescValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке убывания после третьего клика");
//            Collections.sort(sortedRandomValues);
            assertNotEquals(initialValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке возрастания после третьего клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }
    public void SortOfColumnsIP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 12 и 13)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 11 || colIndex == 12) { // Пропуск 12 и 13 столбцов (индексы 11 и 12)
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            List<String> initialValues = new ArrayList<>(originalValues);
            Collections.sort(initialValues);
            assertEquals(initialValues, originalValues, "Значения в столбце " + (colIndex + 1) + " изначально должны быть отсортированы в порядке возрастания");

            // Выполняем клик для сортировки по убыванию
            sortingColumn.inputSortIP.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после сортировки по убыванию
            List<String> sortedDescValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedDescValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения отсортированы по убыванию
            List<String> expectedDescValues = new ArrayList<>(originalValues);
            Collections.sort(expectedDescValues, Collections.reverseOrder());
            assertEquals(expectedDescValues, sortedDescValues, "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

            // Выполняем клик для хаотичной сортировки
            sortingColumn.inputSortIP.click();

            // Ожидание обновления таблицы
            try {
                wait.until(ExpectedConditions.stalenessOf(rows.first()));
            } catch (WebDriverException e) {
                // Игнорируем исключение, если таблица не обновляется как ожидалось
            }

            rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

            // Получение значений после хаотичной сортировки
            List<String> sortedRandomValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                sortedRandomValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения не отсортированы ни по возрастанию, ни по убыванию
            assertNotEquals(originalValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны совпадать с исходными значениями после третьего клика");
            assertNotEquals(expectedDescValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке убывания после третьего клика");
//            Collections.sort(sortedRandomValues);
            assertNotEquals(initialValues, sortedRandomValues, "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы в порядке возрастания после третьего клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

}
