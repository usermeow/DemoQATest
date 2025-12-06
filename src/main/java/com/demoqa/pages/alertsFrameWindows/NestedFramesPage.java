package com.demoqa.pages.alertsFrameWindows;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.elements.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class NestedFramesPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Родительский фрейм (находится на главной странице)
    private final SelenideElement parentFrame = $x("//iframe[@id='frame1']");

    // Текст внутри родительского фрейма (находится прямо в body)
    private final SelenideElement parentText = $x("//body");

    // Дочерний фрейм (находится ВНУТРИ родительского)
    // Ищем по тегу iframe, так как у него может не быть ID внутри srcdoc
    private final SelenideElement childFrame = $x("//iframe[contains(@srcdoc, 'Child Iframe')]");

    // Текст внутри дочернего фрейма
    private final SelenideElement childText = $x("//p");

    public NestedFramesPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Nested Frames")
    public NestedFramesPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Переключиться в родительский фрейм")
    public NestedFramesPage switchToParentFrame() {
        Selenide.switchTo().frame(parentFrame);
        return this;
    }

    @Step("Проверить текст в родительском фрейме: '{expectedText}'")
    public NestedFramesPage verifyParentText(String expectedText) {
        parentText.shouldHave(text(expectedText));
        return this;
    }

    @Step("Переключиться во вложенный (дочерний) фрейм")
    public NestedFramesPage switchToChildFrame() {
        // Мы уже находимся внутри Parent Frame, поэтому ищем iframe внутри него
        // Можно переключиться по индексу 0, так как он там один, или по элементу
        Selenide.switchTo().frame(0);
        return this;
    }

    @Step("Проверить текст в дочернем фрейме: '{expectedText}'")
    public NestedFramesPage verifyChildText(String expectedText) {
        childText.shouldHave(text(expectedText));
        return this;
    }

    @Step("Вернуться на главную страницу (выход из всех фреймов)")
    public NestedFramesPage switchToMainContent() {
        Selenide.switchTo().defaultContent();
        return this;
    }
}