package com.demoqa.pages.alertsFrameWindows;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.elements.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BrowserWindowsPage extends BasePage {

    // --- Локаторы (XPath) ---
    private final SelenideElement newTabButton = $x("//button[@id='tabButton']");
    private final SelenideElement newWindowButton = $x("//button[@id='windowButton']");

    // Элемент заголовка на новой странице (sample page), которая открывается
    private final SelenideElement sampleHeading = $x("//h1[@id='sampleHeading']");

    public BrowserWindowsPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Browser Windows")
    public BrowserWindowsPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Нажать 'New Tab', проверить текст '{expectedText}' и закрыть вкладку")
    public BrowserWindowsPage checkNewTab(String expectedText) {
        newTabButton.shouldBe(visible).click();

        // Переключаемся на второе окно (индекс 1)
        Selenide.switchTo().window(1);

        // Проверяем текст на новой вкладке
        sampleHeading.shouldBe(visible).shouldHave(text(expectedText));

        // Закрываем текущую вкладку
        Selenide.closeWindow();

        // Возвращаемся в основное окно (индекс 0)
        Selenide.switchTo().window(0);

        // Проверяем, что вернулись (кнопка снова видна)
        newTabButton.shouldBe(visible);

        return this;
    }

    @Step("Нажать 'New Window', проверить текст '{expectedText}' и закрыть окно")
    public BrowserWindowsPage checkNewWindow(String expectedText) {
        newWindowButton.shouldBe(visible).click();

        // Логика такая же: для Selenium новое окно и новая вкладка - это просто "handles"
        Selenide.switchTo().window(1);

        sampleHeading.shouldBe(visible).shouldHave(text(expectedText));

        Selenide.closeWindow();
        Selenide.switchTo().window(0);

        newWindowButton.shouldBe(visible);

        return this;
    }
}