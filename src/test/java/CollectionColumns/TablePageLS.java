package CollectionColumns;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class TablePageLS {

    private final SelenideElement table =
            $("#app > div > div.default-layout__content > div.pir-tabs__content" +
            " > div > div > div > div.pir-table__row > div.pir-table__wrapper > " +
                    "div.el-auto-resizer > div > div.el-table__inner-wrapper > " +
            "div.el-table__header-wrapper > table > thead > tr");

    public ElementsCollection columns =
            table.$$("th");


    public ElementsCollection cells =
            $$("div[class$='inner-wrapper']");

    public SelenideElement sorting = $(".pir-table__cell_tool.active");
    public SelenideElement clientsCard = $("div table tbody tr:nth-of-type(2)");
    public SelenideElement infoCard = $("div[style='height: inherit;']");



}
