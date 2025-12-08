package com.demoqa.pages.elements;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class DynamicPropertiesPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Кнопка, которая становится активной (Enabled) через 5 сек
    private final SelenideElement enableAfterButton = $x("//button[@id='enableAfter']");

    // Кнопка, которая меняет цвет текста через 5 сек
    private final SelenideElement colorChangeButton = $x("//button[@id='colorChange']");

    // Кнопка, которая появляется (Visible) через 5 сек
    private final SelenideElement visibleAfterButton = $x("//button[@id='visibleAfter']");


    public DynamicPropertiesPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Dynamic Properties")
    public DynamicPropertiesPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Проверить, что кнопка становится активной (Enabled)")
    public DynamicPropertiesPage verifyButtonBecomesEnabled() {
        // Изначально кнопка может быть disabled, но мы ждем конечного состояния.
        // Увеличиваем таймаут до 6 секунд, так как событие происходит через 5 сек.
        enableAfterButton.shouldBe(enabled, Duration.ofSeconds(6));
        return this;
    }

    @Step("Проверить, что кнопка меняет цвет на красный")
    public DynamicPropertiesPage verifyButtonChangesColor() {
        // Ожидаем появления класса 'text-danger', который отвечает за красный цвет.
        // Это надежнее, чем проверять RGB код (rgba(220, 53, 69, 1)), который может зависеть от браузера.
        colorChangeButton.shouldHave(cssClass("text-danger"), Duration.ofSeconds(6));
        return this;
    }

    @Step("Проверить, что кнопка становится видимой")
    public DynamicPropertiesPage verifyButtonBecomesVisible() {
        // Кнопки нет в DOM или она скрыта, ждем её появления 6 секунд.
        visibleAfterButton.shouldBe(visible, Duration.ofSeconds(6));
        return this;
    }
}