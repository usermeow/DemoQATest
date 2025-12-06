package com.demoqa.tests.allertsFrameWindows;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.tests.elements.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Alerts, Frame & Windows")
@Feature("Alerts Handling")
public class AlertsTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/alerts";

    @Test(description = "Проверка простого Alert")
    @Severity(SeverityLevel.NORMAL)
    public void testSimpleAlert() {
        new AlertsPage(PAGE_URL)
                .openPage()
                .checkSimpleAlert();
        // Тут нет визуального результата на странице для проверки,
        // но если confirm() не сработает (нет алерта), тест упадет с ошибкой.
    }

    @Test(description = "Проверка Alert с таймером (5 секунд)")
    @Severity(SeverityLevel.CRITICAL)
    public void testTimerAlert() {
        new AlertsPage(PAGE_URL)
                .openPage()
                .checkTimerAlert(); // Внутри метода стоит ожидание до 10 секунд
    }

    @Test(description = "Проверка Confirm Box (нажатие OK)")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmBoxAccept() {
        new AlertsPage(PAGE_URL)
                .openPage()
                .checkConfirmAlert(true) // true = нажать OK
                .verifyConfirmResult("You selected Ok");
    }

    @Test(description = "Проверка Confirm Box (нажатие Cancel)")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmBoxDismiss() {
        new AlertsPage(PAGE_URL)
                .openPage()
                .checkConfirmAlert(false) // false = нажать Cancel
                .verifyConfirmResult("You selected Cancel");
    }

    @Test(description = "Проверка Prompt Box (ввод текста)")
    @Severity(SeverityLevel.CRITICAL)
    public void testPromptBox() {
        String name = "Selenide User";

        new AlertsPage(PAGE_URL)
                .openPage()
                .checkPromptAlert(name)
                .verifyPromptResult(name);
    }
}