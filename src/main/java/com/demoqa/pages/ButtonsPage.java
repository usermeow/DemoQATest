package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ButtonsPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Кнопка для двойного клика (имеет стабильный ID)
    private final SelenideElement doubleClickBtn = $x("//button[@id='doubleClickBtn']");

    // Кнопка для правого клика (имеет стабильный ID)
    private final SelenideElement rightClickBtn = $x("//button[@id='rightClickBtn']");

    // Кнопка для простого клика.
    // ВАЖНО: У неё динамический ID (например, #u78s9), который меняется.
    // Поэтому ищем строго по тексту "Click Me".
    // Используем text()='Click Me', а не contains, чтобы не спутать с "Double Click Me".
    private final SelenideElement dynamicClickBtn = $x("//button[text()='Click Me']");

    // --- Локаторы сообщений (появляются после клика) ---
    private final SelenideElement doubleClickMessage = $x("//p[@id='doubleClickMessage']");
    private final SelenideElement rightClickMessage = $x("//p[@id='rightClickMessage']");
    private final SelenideElement dynamicClickMessage = $x("//p[@id='dynamicClickMessage']");

    public ButtonsPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Buttons")
    public ButtonsPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Выполнить двойной клик (Double Click)")
    public ButtonsPage performDoubleClick() {
        doubleClickBtn.shouldBe(visible).doubleClick();
        return this;
    }

    @Step("Выполнить правый клик (Right Click)")
    public ButtonsPage performRightClick() {
        rightClickBtn.shouldBe(visible).contextClick();
        return this;
    }

    @Step("Выполнить простой клик по динамической кнопке")
    public ButtonsPage performDynamicClick() {
        dynamicClickBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить сообщение двойного клика: '{expectedText}'")
    public ButtonsPage verifyDoubleClickMessage(String expectedText) {
        doubleClickMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("Проверить сообщение правого клика: '{expectedText}'")
    public ButtonsPage verifyRightClickMessage(String expectedText) {
        rightClickMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("Проверить сообщение динамического клика: '{expectedText}'")
    public ButtonsPage verifyDynamicClickMessage(String expectedText) {
        dynamicClickMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}