package tests.steps;

import Selectors.SubTask.SubTaskSelectors;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;

public class StepFormsSubTask {

    SubTaskSelectors subTaskSelectors = new SubTaskSelectors();

    @Step("Проверка синхронности создания задачи")
    public void subTask(String task, String result){
        subTaskSelectors.subTask.click();
        subTaskSelectors.tableTask.shouldHave(texts(task));
        subTaskSelectors.tableSearch.click();
        subTaskSelectors.result.shouldHave(text(result));
        subTaskSelectors.closeResult.click();
    }
}
