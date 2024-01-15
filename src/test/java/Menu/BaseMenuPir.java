package Menu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BaseMenuPir {


    public SelenideElement topMenu = $("#app ul");
    private final SelenideElement section = $("#app ul li:nth-child(1)");

    public <BaseMenuPage> BaseMenuPage openSection() {
        section.click();
        return (BaseMenuPage) this;
    }

    public SelenideElement dashboard = $("div[class$='pir-tab-menu']");


}
