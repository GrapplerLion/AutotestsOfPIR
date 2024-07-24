package tests.steps;

import Selectors.SectionCard.CardClientsLSSelectors;
import io.qameta.allure.Step;
import net.datafaker.Faker;

public class StepFormsCardClients {

    CardClientsLSSelectors cardClientsLSSelectors = new CardClientsLSSelectors();
    Faker faker = new Faker();
    String fakeComment = faker.chuckNorris().fact();

    @Step("Переход на клиента и взаимодействие с карточкой ЛС")
    public void CardClients(int numberClients, int numberSectionCard){
        cardClientsLSSelectors.setClients(numberClients);
        cardClientsLSSelectors.setSectionCard(numberSectionCard);
    }

    @Step("Внести изменения в статус абонента")
    public void changesStatusAbonent(int numberStatusType){
        cardClientsLSSelectors.edit.click();
        cardClientsLSSelectors.status.click();
        cardClientsLSSelectors.setSelectStatus(numberStatusType);
        cardClientsLSSelectors.comment.setValue(fakeComment);
        cardClientsLSSelectors.saveButton.click();
    }

}
