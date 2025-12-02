package com.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

// Убрали импорт org.testng.Assert, так как он недоступен в main
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LinksPage extends BasePage {

    // --- Локаторы (XPath) ---

    private final SelenideElement simpleHomeLink = $x("//a[@id='simpleLink']");
    private final SelenideElement createdLink = $x("//a[@id='created']");
    private final SelenideElement noContentLink = $x("//a[@id='no-content']");
    private final SelenideElement notFoundLink = $x("//a[@id='invalid-url']");
    private final SelenideElement linkResponse = $x("//p[@id='linkResponse']");
    private final SelenideElement mainPageBanner = $x("//img[@class='banner-image']");

    // Конструктор
    public LinksPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Links")
    public LinksPage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы для работы с новой вкладкой ---

    @Step("Кликнуть по ссылке 'Home' и переключиться на новую вкладку")
    public LinksPage clickHomeLinkAndSwitchTab() {
        simpleHomeLink.shouldBe(visible).click();

        // Переключаемся на вторую вкладку (индекс 1)
        Selenide.switchTo().window(1);
        return this;
    }

    @Step("Проверить URL текущей вкладки и наличие баннера")
    public LinksPage verifyCurrentUrlAndContent(String expectedUrl) {
        // ИСПРАВЛЕНИЕ: Используем Selenide для проверки URL вместо TestNG Assert
        // Это работает внутри src/main/java и автоматически ждет, пока URL изменится
        webdriver().shouldHave(url(expectedUrl));

        // Проверка, что страница загрузилась (виден баннер)
        mainPageBanner.shouldBe(visible);
        return this;
    }

    @Step("Закрыть текущую вкладку и вернуться на исходную")
    public LinksPage closeTabAndReturn() {
        Selenide.closeWindow(); // Закрываем активную вкладку
        Selenide.switchTo().window(0); // Возвращаемся на первую (исходную)
        return this;
    }

    // --- Методы для работы с API ссылками ---

    @Step("Кликнуть по API ссылке 'Created'")
    public LinksPage clickCreatedLink() {
        createdLink.scrollIntoView(true).click();
        return this;
    }

    @Step("Кликнуть по API ссылке 'Not Found'")
    public LinksPage clickNotFoundLink() {
        notFoundLink.scrollIntoView(true).click();
        return this;
    }

    @Step("Проверить сообщение ответа: '{statusCode}' и '{statusText}'")
    public LinksPage verifyResponse(String statusCode, String statusText) {
        linkResponse.shouldBe(visible);
        // Проверяем, что сообщение содержит и код, и текст
        linkResponse.shouldHave(text(statusCode), text(statusText));
        return this;
    }
}