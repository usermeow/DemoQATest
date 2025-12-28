package com.demoqa.pages.widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x; // Добавляем импорт для $$x

public class SelectMenuPage extends BasePage {

    // --- Локаторы (XPath & CSS) ---

    // 1. Select Value (React Select)
    private final SelenideElement selectValueInput = $("#react-select-2-input");
    private final SelenideElement selectValueContainer = $("#withOptGroup");

    // 2. Select One (React Select)
    private final SelenideElement selectOneInput = $("#react-select-3-input");
    private final SelenideElement selectOneContainer = $("#selectOne");

    // 3. Old Style Select Menu (Standard HTML select)
    private final SelenideElement oldSelectMenu = $("#oldSelectMenu");

    // 4. Multiselect Dropdown (React Select Multiple)
    private final SelenideElement multiSelectInput = $("#react-select-4-input");

    // ИСПРАВЛЕНИЕ ОШИБКИ: Упрощаем локатор, убирая нестабильный CSS-in-JS класс (css-1rhbuit-multiValue).
    // Теперь ищет любой элемент с классом 'multi-value__label', который отображает выбранный "чип".
    private final ElementsCollection multiSelectValues = $$x("//div[contains(@class, 'multi-value__label')]");

    // 5. Standard Multi Select (Standard HTML select multiple)
    private final SelenideElement standardMultiSelect = $("#cars");


    public SelectMenuPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Select Menu")
    public SelectMenuPage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы ---

    @Step("Select Value: Выбрать опцию '{optionText}'")
    public SelectMenuPage selectGroupOption(String optionText) {
        // Для React Select надежнее всего ввести текст в скрытый input и нажать Enter
        selectValueInput.setValue(optionText).pressEnter();
        return this;
    }

    @Step("Проверить значение в Select Value: '{expectedText}'")
    public SelectMenuPage verifySelectValue(String expectedText) {
        // Проверяем текст в контейнере (он отображается как single-value)
        selectValueContainer.shouldHave(text(expectedText));
        return this;
    }

    @Step("Select One: Выбрать опцию '{optionText}'")
    public SelectMenuPage selectOneOption(String optionText) {
        selectOneInput.setValue(optionText).pressEnter();
        return this;
    }

    @Step("Проверить значение в Select One: '{expectedText}'")
    public SelectMenuPage verifySelectOne(String expectedText) {
        selectOneContainer.shouldHave(text(expectedText));
        return this;
    }

    @Step("Old Style Select: Выбрать цвет '{color}'")
    public SelectMenuPage selectOldStyleColor(String color) {
        // Стандартный селект - используем встроенный метод Selenide
        oldSelectMenu.selectOption(color);
        return this;
    }

    @Step("Проверить значение в Old Style Select: '{expectedColor}'")
    public SelectMenuPage verifyOldStyleColor(String expectedColor) {
        oldSelectMenu.getSelectedOption().shouldHave(text(expectedColor));
        return this;
    }

    @Step("Multiselect: Добавить цвета {colors}")
    public SelectMenuPage selectMultiColors(String... colors) {
        for (String color : colors) {
            multiSelectInput.setValue(color).pressEnter();
        }
        return this;
    }

    @Step("Проверить выбранные цвета в Multiselect: {expectedColors}")
    public SelectMenuPage verifyMultiSelectColors(String... expectedColors) {
        // Проверяем, что коллекция выбранных элементов содержит тексты
        for (String color : expectedColors) {
            // Используем find, чтобы убедиться, что такой элемент есть в коллекции
            multiSelectValues.find(text(color)).shouldBe(visible);
        }
        return this;
    }

    @Step("Standard Multi Select: Выбрать автомобили {cars}")
    public SelectMenuPage selectStandardCars(String... cars) {
        // ИСПРАВЛЕНИЕ ОШИБКИ 2: Перебираем массив и выбираем опции по одной.
        // Перегрузка selectOption(String...) в Selenide ожидает varargs, а не массив.
        for (String car : cars) {
            standardMultiSelect.selectOption(car);
        }
        return this;
    }

    // Метод проверки для стандартного мультиселекта (опционально)
}