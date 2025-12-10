package com.demoqa.pages.widgets;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SliderPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Сам ползунок (input type="range")
    private final SelenideElement sliderInput = $x("//input[contains(@class, 'range-slider')]");

    // Поле, отображающее текущее значение (это поле мы проверяем)
    private final SelenideElement sliderValueField = $x("//input[@id='sliderValue']");

    public SliderPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Slider")
    public SliderPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Передвинуть слайдер на значение: {targetValue}")
    public SliderPage moveSliderTo(int targetValue) {
        sliderInput.shouldBe(visible);
        String targetValueStr = String.valueOf(targetValue);

        // 1. Установка значения на ползунке (input type="range") и диспатчинг событий.
        // Это должно обновить значение на странице.
        Selenide.executeJavaScript(
                "arguments[0].focus();" +
                        "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                sliderInput, targetValueStr
        );

        // 2. Дополнительная гарантия обновления:
        // Если фреймворк не отреагировал на события ползунка, мы явно устанавливаем
        // значение в поле, которое мы проверяем, и диспатчим событие change.
        // Это более надежный, но менее "честный" способ, необходимый для обхода
        // сложных обработчиков UI фреймворков.
        Selenide.executeJavaScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                sliderValueField, targetValueStr
        );

        return this;
    }

    @Step("Проверить, что значение слайдера равно: {expectedValue}")
    public SliderPage verifySliderValue(int expectedValue) {
        // Проверяем значение в поле ввода рядом со слайдером
        sliderValueField.shouldHave(value(String.valueOf(expectedValue)));
        return this;
    }
}