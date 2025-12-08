package com.demoqa.pages.widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Arrays;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AutoCompletePage extends BasePage {

    // --- Локаторы (XPath) ---

    // Поле для множественного выбора (Multiple)
    private final SelenideElement multipleInput = $x("//input[@id='autoCompleteMultipleInput']");
    // Контейнер, где появляются выбранные плашки (чипсы) цветов
    private final ElementsCollection selectedMultiValues = $x("//div[@id='autoCompleteMultipleContainer']").$$x(".//div[contains(@class, 'auto-complete__multi-value__label')]");

    // Поле для одиночного выбора (Single)
    private final SelenideElement singleInput = $x("//input[@id='autoCompleteSingleInput']");
    // Контейнер, где отображается выбранное значение
    private final SelenideElement selectedSingleValue = $x("//div[contains(@class, 'auto-complete__single-value')]");

    // Выпадающее меню с подсказками (общее для обоих полей)
    // Появляется только после ввода текста
    private final SelenideElement suggestionsMenu = $x("//div[contains(@class, 'auto-complete__menu')]");

    public AutoCompletePage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Auto Complete")
    public AutoCompletePage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы для Multiple Input ---

    @Step("Ввести '{partialText}' в поле Multiple и выбрать '{colorToSelect}'")
    public AutoCompletePage addColor(String partialText, String colorToSelect) {
        multipleInput.shouldBe(visible).setValue(partialText);

        // Ждем появления меню и выбираем нужный цвет
        selectOptionFromMenu(colorToSelect);
        return this;
    }

    @Step("Проверить, что в поле Multiple выбраны цвета: {expectedColors}")
    public AutoCompletePage verifyMultipleColors(String... expectedColors) {
        // Проверяем текст всех выбранных "чипсов"
        selectedMultiValues.shouldHave(texts(Arrays.asList(expectedColors)));
        return this;
    }

    // --- Методы для Single Input ---

    @Step("Ввести '{partialText}' в поле Single и выбрать '{colorToSelect}'")
    public AutoCompletePage setSingleColor(String partialText, String colorToSelect) {
        singleInput.shouldBe(visible).setValue(partialText);
        selectOptionFromMenu(colorToSelect);
        return this;
    }

    @Step("Проверить, что в поле Single выбран цвет: '{expectedColor}'")
    public AutoCompletePage verifySingleColor(String expectedColor) {
        selectedSingleValue.shouldBe(visible).shouldHave(text(expectedColor));
        return this;
    }

    // --- Вспомогательный метод ---

    private void selectOptionFromMenu(String optionText) {
        suggestionsMenu.shouldBe(visible, Duration.ofSeconds(5));

        // Ищем опцию внутри меню, которая содержит нужный текст, и кликаем
        // Используем .//div, так как опции - это div элементы внутри меню
        suggestionsMenu.$x(".//div[contains(text(), '" + optionText + "')]")
                .shouldBe(visible)
                .click();
    }
}