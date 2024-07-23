package Selectors.RegPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPageSelectors {

    public static final String baseUrl = "http://10.1.115.115";


    //TODO: Селекторы для проверки формы регистрации

    public SelenideElement pirBackground = $("html.login-pf");
    public SelenideElement pirWindowReg = $("div.card-pf");

    //TODO: Селекторы для заполнения формы регистрации
    private final SelenideElement firstNameInput = $("#username");
    private final SelenideElement lastNameInput = $("#password");
    private final SelenideElement InputLogin = $("#kc-login");


    public void openPage() {
        open(baseUrl);
        String TITLE_TEXT = "Sign in to your account";
        $("#kc-page-title").shouldHave(text(TITLE_TEXT));
    }


    public void setFirstName(String value) {
        firstNameInput.setValue(value);

    }

    public void setLastName(String value) {
        lastNameInput.setValue(value);

    }
    public void setInputLogin() {
        InputLogin.click();

    }

}