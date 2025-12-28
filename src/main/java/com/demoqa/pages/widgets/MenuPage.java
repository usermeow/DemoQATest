package com.demoqa.pages.widgets;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MenuPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Элементы верхнего уровня
    private final SelenideElement mainItem1 = $x("//a[text()='Main Item 1']");
    private final SelenideElement mainItem2 = $x("//a[text()='Main Item 2']");
    private final SelenideElement mainItem3 = $x("//a[text()='Main Item 3']");

    // Элементы второго уровня (внутри Main Item 2)
    // Используем text(), так как ID там нет или они неудобные
    private final SelenideElement subItem1 = $x("//a[text()='Sub Item']"); // Первый саб-айтем
    private final SelenideElement subItem2 = $x("//a[text()='Sub Item'][2]"); // Если текст дублируется, используем индекс (или более точный путь)
    private final SelenideElement subSubList = $x("//a[contains(text(), 'SUB SUB LIST')]");

    // Элементы третьего уровня (внутри SUB SUB LIST)
    private final SelenideElement subSubItem1 = $x("//a[text()='Sub Sub Item 1']");
    private final SelenideElement subSubItem2 = $x("//a[text()='Sub Sub Item 2']");

    public MenuPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Menu")
    public MenuPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Навести курсор на 'Main Item 2'")
    public MenuPage hoverMainItem2() {
        mainItem2.shouldBe(visible).hover();
        return this;
    }

    @Step("Проверить, что подменю 'Sub Item' стало видимым")
    public MenuPage verifySubItemVisible() {
        // Проверяем первый элемент подменю
        // На странице demoqa есть два элемента с текстом "Sub Item", берем первый попавшийся в контексте
        $x("//ul/li/a[text()='Sub Item']").shouldBe(visible);
        return this;
    }

    @Step("Навести курсор на 'SUB SUB LIST »'")
    public MenuPage hoverSubSubList() {
        // Этот элемент становится виден только после hover на Main Item 2
        subSubList.shouldBe(visible).hover();
        return this;
    }

    @Step("Проверить, что элементы 'Sub Sub Item 1' и '2' стали видимы")
    public MenuPage verifySubSubItemsVisible() {
        subSubItem1.shouldBe(visible);
        subSubItem2.shouldBe(visible);
        return this;
    }
}