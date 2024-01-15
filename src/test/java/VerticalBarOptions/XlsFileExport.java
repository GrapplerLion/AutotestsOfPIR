package VerticalBarOptions;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class XlsFileExport {

    //путь к выгрузке файла через настройки фильтра в колонке "Абонент"
    public SelenideElement columnsAbonent = $("th[class='el-table_1_column_4 is-leaf pir-table__cell-header el-table__cell']");

    public SelenideElement filterAbonent = $("html > body > div:nth-of-type(1) > div > div:nth-of-type(2) > div:nth-of-type(2) > div > div > div > div:nth-of-type(2) > div:nth-of-type(1) > div:nth-of-type(1) > div > div:nth-of-type(1) > div:nth-of-type(2) > table > thead > tr > th:nth-of-type(4) > div > span:nth-of-type(3) > i > svg");

    public SelenideElement inputTypeAbonent = $("html > body > div:nth-of-type(2) > div:nth-of-type(7) > div > div > div:nth-of-type(1) > div > div > div:nth-of-type(1) > div:nth-of-type(1) > div > div > div > div > span > span > i > svg");
    public SelenideElement inputTypeEquals = $("html > body > div:nth-of-type(2) > div:nth-of-type(20) > div > div > div:nth-of-type(1)").$(byText("Равно"));

    public SelenideElement xls = $("div[class$='pir-table__bar'] ul li:nth-of-type(3) > button");
    public SelenideElement inputButton = $("html > body > div:nth-of-type(2) > div:nth-of-type(7) > div > div > div:nth-of-type(1) > div > div > div:nth-of-type(4) > button:nth-of-type(2)");

    public SelenideElement textOfNumber = $("div[class='pir-pagination']");
    public SelenideElement buttonOfExport = $("div[class$='pir-table__bar'] ul li:nth-of-type(3) > button");
    public SelenideElement flex = $("html > body > div:nth-of-type(2) > div:nth-of-type(7) > div > div > div:nth-of-type(1) > div > div > div:nth-of-type(1) > div:nth-of-type(2) > div > div");

}
