package HorizontalPanelOptions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class UpdateTable {

    public SelenideElement updateTableIcon = $("svg[class$='icon-tabler-refresh']");
    public final ElementsCollection numberOfAccounts = $$("tbody tr");
    public int getNumberOfAccounts() {
        return numberOfAccounts.shouldHave(size(20)).size();
    }
//    public final initialText = numberOfAccounts.text();
}
