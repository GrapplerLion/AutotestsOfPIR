package Selectors.Menu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;


public class BaseMenuPirSelectors {


    public SelenideElement topMenu = $("#app ul");
    public SelenideElement dashboard = $("div[class$='pir-tab-menu']");

    // Метод для открытия раздела по номеру
    public void openSection(int sectionNumber) {
        SelenideElement section = topMenu.$("li:nth-child(" + sectionNumber + ")");
        section.click();
    }

}
