package tests.steps;

import Selectors.SortColumns.SortingColumnSelectors;
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
import java.util.List;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.junit.jupiter.api.Assertions.*;

public class StepFormsSorting {

    private final SortingColumnSelectors sortingColumnSelectors = new SortingColumnSelectors();


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
        ElementsCollection rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int colIndex = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size() - 1; // Индекс последнего столбца

        // Выполняем клик для сортировки по убыванию
        sortingColumnSelectors.inputSortLS.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по убыванию
        List<String> sortedDescValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            sortedDescValues.add(row.$$("td").get(colIndex).getText());
        }

        // Проверка, что значения отсортированы по убыванию
        assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

        // Выполняем клик для сортировки по возрастанию
        sortingColumnSelectors.inputSortLS.click();
        sortingColumnSelectors.inputSortLS.click();


        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по возрастанию
        List<String> sortedAscValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            sortedAscValues.add(row.$$("td").get(colIndex).getText());
        }

        // Проверка, что значения отсортированы по возрастанию
        assertTrue(isSortedAscending(sortedAscValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

    @Step("Сортировка колонок - Аналитические показатели")
    public void SortOfColumnsAP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int colIndex = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size() - 1; // Индекс последнего столбца

        // Выполняем клик для сортировки по убыванию
        sortingColumnSelectors.inputSortAP.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по убыванию
        List<String> sortedDescValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            sortedDescValues.add(row.$$("td").get(colIndex).getText());
        }

        // Проверка, что значения отсортированы по убыванию
        assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

        // Выполняем клик для сортировки по возрастанию
        sortingColumnSelectors.inputSortAP.click();
        sortingColumnSelectors.inputSortAP.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по возрастанию
        List<String> sortedAscValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            sortedAscValues.add(row.$$("td").get(colIndex).getText());
        }

        // Проверка, что значения отсортированы по возрастанию
        assertTrue(isSortedAscending(sortedAscValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

    @Step("Сортировка колонок - Досудебная работа")
    public void SortOfColumnsDR() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int colIndex = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size() - 1; // Индекс последнего столбца

        // Выполняем клик для сортировки по убыванию
        sortingColumnSelectors.inputSortDR.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по убыванию
        List<String> sortedDescValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            ElementsCollection cells = row.$$("td");
            if (cells.size() > colIndex) { // Проверка на допустимый индекс
                sortedDescValues.add(cells.get(colIndex).getText());
            }
        }

        // Проверка, что значения отсортированы по убыванию
        assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

        // Выполняем клик для сортировки по возрастанию
        sortingColumnSelectors.inputSortDR.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по возрастанию
        List<String> sortedAscValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            ElementsCollection cells = row.$$("td");
            if (cells.size() > colIndex) { // Проверка на допустимый индекс
                sortedAscValues.add(cells.get(colIndex).getText());
            }
        }

        // Проверка, что значения отсортированы по возрастанию
        assertTrue(isSortedAscending(sortedAscValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

    @Step("Сортировка колонок - Судопроизводство")
    public void SortOfColumnsSP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int colIndex = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size() - 1; // Индекс последнего столбца

        // Выполняем клик для сортировки по убыванию
        sortingColumnSelectors.inputSortSP.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по убыванию
        List<String> sortedDescValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            sortedDescValues.add(row.$$("td").get(colIndex).getText());
        }

        // Проверка, что значения отсортированы по убыванию
        assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

        // Выполняем клик для сортировки по возрастанию
        sortingColumnSelectors.inputSortSP.click();
        sortingColumnSelectors.inputSortSP.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по возрастанию
        List<String> sortedAscValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            sortedAscValues.add(row.$$("td").get(colIndex).getText());
        }

        // Проверка, что значения отсортированы по возрастанию
        assertTrue(isSortedAscending(sortedAscValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

    @Step("Сортировка колонок - Исполнительное производство")
    public void SortOfColumnsIP() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Получение количества строк и столбцов в таблице
        ElementsCollection rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0));
        int colIndex = rows.first().$$("td").shouldBe(sizeGreaterThan(0)).size() - 1; // Индекс последнего столбца

        // Выполняем клик для сортировки по убыванию
        sortingColumnSelectors.inputSortIP.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по убыванию
        List<String> sortedDescValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            ElementsCollection cells = row.$$("td");
            if (cells.size() > colIndex) { // Проверка на допустимый индекс
                sortedDescValues.add(cells.get(colIndex).getText());
            }
        }

        // Проверка, что значения отсортированы по убыванию
        assertTrue(isSortedDescending(sortedDescValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке убывания");

        // Выполняем клик для сортировки по возрастанию
        sortingColumnSelectors.inputSortIP.click();
        sortingColumnSelectors.inputSortIP.click();

        // Ожидание обновления таблицы
        try {
            wait.until(ExpectedConditions.stalenessOf(rows.first()));
        } catch (WebDriverException e) {
            // Игнорируем исключение, если таблица не обновляется как ожидалось
        }

        rows = sortingColumnSelectors.containsOfValueColumns.shouldBe(sizeGreaterThan(0)); // Перезапрос строк после клика

        // Получение значений после сортировки по возрастанию
        List<String> sortedAscValues = new ArrayList<>();
        for (SelenideElement row : rows) {
            ElementsCollection cells = row.$$("td");
            if (cells.size() > colIndex) { // Проверка на допустимый индекс
                sortedAscValues.add(cells.get(colIndex).getText());
            }
        }

        // Проверка, что значения отсортированы по возрастанию
        assertTrue(isSortedAscending(sortedAscValues), "Значения в столбце " + (colIndex + 1) + " должны быть отсортированы в порядке возрастания");

        // Вывод сообщения об успешном прохождении теста
        System.out.println("Проверка сортировки колонок прошла успешно.");
    }

}
