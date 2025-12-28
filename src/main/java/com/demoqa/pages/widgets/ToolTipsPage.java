package com.demoqa.pages.widgets;


import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ToolTipsPage extends BasePage {

    // --- Локаторы элементов для наведения ---
    private final SelenideElement hoverButton = $("#toolTipButton");
    private final SelenideElement hoverInput = $("#toolTipTextField");

    // Ссылки внутри текста (ищем по тексту ссылки)
    private final SelenideElement contraryLink = $x("//a[text()='Contrary']");
    private final SelenideElement sectionLink = $x("//a[text()='1.10.32']");

    // --- Локатор самого тултипа ---
    // На DemoQA (Bootstrap) тултип — это div с классом 'tooltip-inner', который появляется в конце body
    private final SelenideElement tooltip = $(".tooltip-inner");

    public ToolTipsPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Tool Tips")
    public ToolTipsPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Навести курсор на кнопку 'Hover me to see'")
    public ToolTipsPage hoverOverButton() {
        hoverButton.shouldBe(visible).hover();
        return this;
    }

    @Step("Навести курсор на текстовое поле")
    public ToolTipsPage hoverOverInput() {
        hoverInput.shouldBe(visible).hover();
        return this;
    }

    @Step("Навести курсор на ссылку 'Contrary'")
    public ToolTipsPage hoverOverContraryLink() {
        contraryLink.shouldBe(visible).hover();
        return this;
    }

    @Step("Проверить, что появился тултип с текстом: '{expectedText}'")
    public ToolTipsPage verifyTooltipText(String expectedText) {
        // Тултип появляется с анимацией, поэтому ждем его видимости
        tooltip.shouldBe(visible, Duration.ofSeconds(2)).shouldHave(text(expectedText));
        return this;
    }
}