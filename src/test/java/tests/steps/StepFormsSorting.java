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
import static org.junit.jupiter.api.Assertions.*;

public class StepFormsSorting {

    private final SortingColumn sortingColumn = new SortingColumn();

    private boolean isSortedDescending(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isSortedAscending(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    @Step("Сортировка колонок - Лицевой счёт")
    public void SortOfColumnsLS() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 0, 1, 3, 4, 5, 6)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 0 || colIndex == 1 || colIndex == 3 || colIndex == 4 || colIndex == 5 || colIndex == 6) {
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            assertTrue(isSortedAscending(originalValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

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
            assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

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
            assertFalse(isSortedAscending(sortedRandomValues) || isSortedDescending(sortedRandomValues), "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы после второго клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

    @Step("Сортировка колонок - Аналитические показатели")
    public void SortOfColumnsAP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 0, 2, 4, 5, 6)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 0 || colIndex == 2 || colIndex == 4 || colIndex == 5 || colIndex == 6) {
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            assertTrue(isSortedAscending(originalValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

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
            assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

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
            assertFalse(isSortedAscending(sortedRandomValues) || isSortedDescending(sortedRandomValues), "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы после второго клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

    @Step("Сортировка колонок - Досудебная работа")
    public void SortOfColumnsDR() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 0, 1, 2)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 0 || colIndex == 1 || colIndex == 2) {
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            assertTrue(isSortedAscending(originalValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

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
            assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

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
            assertFalse(isSortedAscending(sortedRandomValues) || isSortedDescending(sortedRandomValues), "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы после второго клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

    @Step("Сортировка колонок - Судопроизводство")
    public void SortOfColumnsSP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 3, 4)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            if (colIndex == 3 || colIndex == 4) {
                continue;
            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            assertTrue(isSortedAscending(originalValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

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
            assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

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
            assertFalse(isSortedAscending(sortedRandomValues) || isSortedDescending(sortedRandomValues), "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы после второго клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

    @Step("Сортировка колонок - Исполнительное производство")
    public void SortOfColumnsIP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumn.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int numberOfColumns = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size();

        // Проверка сортировки для каждого столбца (кроме 1, 0, 3, 4, 5, 6)
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
//            if (colIndex == 1 || colIndex == 0 || colIndex == 3 || colIndex == 4 || colIndex == 5 || colIndex == 6) {
//                continue;
//            }

            // Получение значений до сортировки
            List<String> originalValues = new ArrayList<>();
            for (SelenideElement row : rows) {
                originalValues.add(row.$$("td").get(colIndex).getText());
            }

            // Проверка, что значения изначально отсортированы по возрастанию
            assertTrue(isSortedAscending(originalValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

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
            assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

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
            assertFalse(isSortedAscending(sortedRandomValues) || isSortedDescending(sortedRandomValues), "Значения в столбце " + (colIndex + 1) + " не должны быть отсортированы после второго клика");

        }

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

}
