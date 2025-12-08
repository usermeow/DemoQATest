package com.demoqa.pages.alertsFrameWindows;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AlertsPage extends BasePage {

    // --- Локаторы (XPath) ---
    private final SelenideElement alertButton = $x("//button[@id='alertButton']");
    private final SelenideElement timerAlertButton = $x("//button[@id='timerAlertButton']");
    private final SelenideElement confirmButton = $x("//button[@id='confirmButton']");
    private final SelenideElement promptButton = $x("//button[@id='promtButton']"); // На сайте опечатка в ID: 'promt'

    // Результаты (появляются после действий)
    private final SelenideElement confirmResult = $x("//span[@id='confirmResult']");
    private final SelenideElement promptResult = $x("//span[@id='promptResult']");

    public AlertsPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Alerts")
    public AlertsPage openPage() {
        super.openPage();
        return this;
    }

    // --- 1. Обычный Alert ---

    @Step("Вызвать обычный Alert и принять его")
    public AlertsPage checkSimpleAlert() {
        alertButton.click();
        // confirm() автоматически нажимает ОК в алерте
        Selenide.confirm();
        return this;
    }

    // --- 2. Alert с таймером (5 секунд) ---

    @Step("Вызвать Alert с задержкой и дождаться его появления")
    public AlertsPage checkTimerAlert() {
        timerAlertButton.click();

        // Так как алерт появляется через 5 сек, а дефолтный таймаут 4 сек,
        // нужно явно подождать появление алерта (ставим 10 сек для надежности)
        Alert alert = Selenide.Wait()
                .withTimeout(Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

        // Принимаем алерт через стандартный Selenium интерфейс, так как мы его уже получили
        alert.accept();
        return this;
    }

    // --- 3. Confirm Box (OK / Cancel) ---

    @Step("Вызвать Confirm Box и выбрать: {action} (true=OK, false=Cancel)")
    public AlertsPage checkConfirmAlert(boolean accept) {
        confirmButton.click();

        if (accept) {
            // Нажимаем OK
            Selenide.confirm();
        } else {
            // Нажимаем Cancel
            Selenide.dismiss();
        }
        return this;
    }

    @Step("Проверить результат Confirm: '{expectedText}'")
    public AlertsPage verifyConfirmResult(String expectedText) {
        confirmResult.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    // --- 4. Prompt Box (Ввод текста) ---

    @Step("Вызвать Prompt Box и ввести текст: '{text}'")
    public AlertsPage checkPromptAlert(String text) {
        promptButton.click();
        // Вводим текст и нажимаем OK одной командой
        Selenide.prompt(text);
        return this;
    }

    @Step("Проверить результат Prompt: '{enteredText}'")
    public AlertsPage verifyPromptResult(String enteredText) {
        promptResult.shouldBe(visible).shouldHave(text("You entered " + enteredText));
        return this;
    }
}