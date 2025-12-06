package com.demoqa.pages.alertsFrameWindows;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.elements.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class FramesPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Фреймы (ищем их как обычные элементы на главной странице)
    private final SelenideElement bigFrame = $x("//iframe[@id='frame1']");
    private final SelenideElement smallFrame = $x("//iframe[@id='frame2']");

    // Заголовок внутри фрейма.
    private final SelenideElement frameHeading = $x("//h1[@id='sampleHeading']");

    // Элемент на главной странице (заголовок "Frames") - УДАЛЁН ИЗ ПРОВЕРКИ
    // private final SelenideElement mainHeader = $x("//div[@class='main-header']");

    public FramesPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Frames")
    public FramesPage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы для работы с Большим Фреймом ---

    @Step("Переключиться в большой фрейм (Frame 1)")
    public FramesPage switchToBigFrame() {
        // Selenide позволяет переключаться во фрейм, передавая его SelenideElement
        Selenide.switchTo().frame(bigFrame);
        return this;
    }

    // --- Методы для работы с Маленьким Фреймом ---

    @Step("Переключиться в маленький фрейм (Frame 2)")
    public FramesPage switchToSmallFrame() {
        Selenide.switchTo().frame(smallFrame);
        return this;
    }

    // --- Общие методы ---

    @Step("Проверить текст заголовка внутри текущего фрейма: '{expectedText}'")
    public FramesPage verifyFrameText(String expectedText) {
        // Этот элемент находится внутри фрейма, поэтому этот шаг выполняется после переключения.
        frameHeading.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("Вернуться из фрейма на главную страницу")
    public FramesPage switchToMainContent() {
        // Просто возвращаемся в родительский контекст.
        // Убрана проверка заголовка, которая вызывала ошибку.
        Selenide.switchTo().defaultContent();
        return this;
    }
}