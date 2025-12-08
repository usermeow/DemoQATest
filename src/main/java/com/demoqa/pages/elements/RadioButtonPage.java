package com.demoqa.pages.elements;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class RadioButtonPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Кликать нужно по label, так как input перекрыт или невидим
    // Используем атрибут 'for', который связывает label с input
    private final SelenideElement yesLabel = $x("//label[@for='yesRadio']");
    private final SelenideElement impressiveLabel = $x("//label[@for='impressiveRadio']");

    // Для проверки 'disabled' нам нужен сам input, а не label
    private final SelenideElement noRadioInput = $x("//input[@id='noRadio']");
    // А если бы мы хотели кликнуть (если бы он был активен), то нужен label
    private final SelenideElement noLabel = $x("//label[@for='noRadio']");

    // Локатор текста результата
    private final SelenideElement outputText = $x("//span[@class='text-success']");


    public RadioButtonPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Radio Button")
    public RadioButtonPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Выбрать опцию 'Yes'")
    public RadioButtonPage selectYes() {
        yesLabel.shouldBe(visible).click();
        return this;
    }

    @Step("Выбрать опцию 'Impressive'")
    public RadioButtonPage selectImpressive() {
        impressiveLabel.shouldBe(visible).click();
        return this;
    }

    @Step("Попытаться кликнуть на 'No' (не должно сработать)")
    public RadioButtonPage selectNo() {
        // Мы можем кликнуть по лейблу, даже если инпут выключен, но ничего не произойдет
        noLabel.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что отображается результат: '{expectedText}'")
    public RadioButtonPage verifyOutput(String expectedText) {
        outputText.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("Проверить, что радио-кнопка 'No' недоступна (disabled)")
    public RadioButtonPage verifyNoIsDisabled() {
        // Проверяем атрибут disabled у input элемента
        noRadioInput.shouldHave(attribute("disabled"));
        // Или используем встроенное условие Selenide
        noRadioInput.shouldBe(disabled);
        return this;
    }
}