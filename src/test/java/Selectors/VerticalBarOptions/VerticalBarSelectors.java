package Selectors.VerticalBarOptions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VerticalBarSelectors {

    public SelenideElement stendUpColumn = $("div[class$='pir-table__bar'] ul li:nth-of-type(2) > button");
    public SelenideElement columnOfHeader = $("div.table-bar__view div.pir-row-info__header");
    public ElementsCollection allColumnsName = $$("div.table-bar__view div.pir-row-info__content div span.item__prop-name");
    public ElementsCollection allColumnsNumber = $$("div.table-bar__view div.pir-row-info__content div.item-info .item");

}
