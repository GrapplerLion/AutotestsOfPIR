package tests.steps;

import Selectors.PlaningAP.PlaningAPSelectors;
import io.qameta.allure.Step;
import net.datafaker.Faker;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;

public class StepFormsPlaningAP {

    Faker faker = new Faker();
    String fakeComment = faker.chuckNorris().fact();
    PlaningAPSelectors planingAPSelectors = new PlaningAPSelectors();

    @Step("Планирование СМС")
    public void planingSaveSms(){
        planingAPSelectors.chekbox.shouldBe(enabled, Duration.ofSeconds(5)).click();
        planingAPSelectors.openPlaning.shouldBe(enabled, Duration.ofSeconds(5)).click();
        planingAPSelectors.setTypePlaning(1);
        planingAPSelectors.comment.shouldBe(enabled, Duration.ofSeconds(5)).setValue(fakeComment);
        planingAPSelectors.sample.shouldBe(enabled, Duration.ofSeconds(5)).click();
        planingAPSelectors.setTypeSample(1);
        planingAPSelectors.button.shouldBe(enabled, Duration.ofSeconds(5)).click();

    }

}
