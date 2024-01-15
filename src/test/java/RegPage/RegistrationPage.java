package RegPage;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final String TITLE_TEXT = "Sign in to your account";
    public static final String baseUrl = "http://10.1.115.162:8080/";


    //TODO: Селекторы для проверки формы регистрации

    public SelenideElement pirBackground = $("html.login-pf");
    public SelenideElement pirWindowReg = $("div.card-pf");

    //TODO: Селекторы для заполнения формы регистрации
    private final SelenideElement firstNameInput = $("#username");
    private final SelenideElement lastNameInput = $("#password");
    private final SelenideElement InputLogin = $("#kc-login");


    public RegistrationPage openPage() {
        open(baseUrl);
        $("#kc-page-title").shouldHave(text(TITLE_TEXT));
        return this;
    }


    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }
    public RegistrationPage setInputLogin() {
        InputLogin.click();

        return this;
    }

}
