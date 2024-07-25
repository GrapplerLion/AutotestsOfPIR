package tests.AnalyticalTests;

import org.junit.jupiter.api.Test;
import tests.steps.StepFormsFilterLS;
import tests.steps.StepFormsPlaningAP;
import tests.steps.StepFormsSubTask;

public class AnalyticalIndicatorsTestCase {

    StepFormsPlaningAP stepFormsPlaningAP = new StepFormsPlaningAP();
    StepFormsFilterLS stepFormsFilterLS = new StepFormsFilterLS();
    StepFormsSubTask stepFormsSubTask = new StepFormsSubTask();

    @Test
    public void testPlaningSMS(){
        stepFormsFilterLS.Authorization("admin", "admin");
        stepFormsFilterLS.BaseMenu(2);
        stepFormsPlaningAP.planingSaveSms(1,1);
        stepFormsSubTask.subTask("Аналитические показатели. Планирование рассылки sms", "Завершено успешно.");
    }
}
