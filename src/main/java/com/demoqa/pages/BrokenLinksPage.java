package com.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class BrokenLinksPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Валидная картинка (логотип ToolsQA в контенте)
    // Ищем img, который идет сразу после текста 'Valid image'
    private final SelenideElement validImage = $x("//p[text()='Valid image']/following-sibling::img[1]");

    // Сломанная картинка
    // Ищем img, который идет сразу после текста 'Broken image'
    private final SelenideElement brokenImage = $x("//p[text()='Broken image']/following-sibling::img[1]");

    // Валидная ссылка
    private final SelenideElement validLink = $x("//a[text()='Click Here for Valid Link']");

    // Сломанная ссылка
    private final SelenideElement brokenLink = $x("//a[text()='Click Here for Broken Link']");

    // Заголовок на странице 500 (куда ведет сломанная ссылка на demoqa)
    // Обычно там текст "Status Codes" или "500"
    private final SelenideElement errorPageHeader = $x("//h3[contains(text(),'Status Codes')] | //div[contains(text(),'500')]");

    public BrokenLinksPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Broken Links")
    public BrokenLinksPage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы для работы с картинками ---

    @Step("Проверить, что 'Valid image' отображается корректно")
    public BrokenLinksPage verifyValidImage() {
        validImage.shouldBe(visible);
        boolean isBroken = isImageBroken(validImage);

        if (isBroken) {
            throw new AssertionError("Ожидалась валидная картинка, но она сломана (naturalWidth = 0)");
        }
        return this;
    }

    @Step("Проверить, что 'Broken image' действительно сломана")
    public BrokenLinksPage verifyBrokenImage() {
        brokenImage.shouldBe(visible);
        boolean isBroken = isImageBroken(brokenImage);

        if (!isBroken) {
            throw new AssertionError("Ожидалась сломанная картинка, но она загрузилась корректно");
        }
        return this;
    }

    // Приватный метод для проверки naturalWidth через JS
    private boolean isImageBroken(SelenideElement image) {
        return (Boolean) Selenide.executeJavaScript(
                "return arguments[0].naturalWidth === 0", image);
    }

    // --- Методы для работы со ссылками ---

    @Step("Кликнуть по валидной ссылке и проверить переход")
    public BrokenLinksPage clickValidLinkAndVerify() {
        validLink.scrollIntoView(true).click();

        // Проверяем, что перешли на главную (demoqa.com)
        webdriver().shouldHave(url("https://demoqa.com/"));

        // Возвращаемся назад для продолжения теста
        Selenide.back();
        return this;
    }

    @Step("Кликнуть по сломанной ссылке и проверить URL ошибки")
    public BrokenLinksPage clickBrokenLinkAndVerify() {
        brokenLink.scrollIntoView(true).click();

        // Ссылка ведет на http://the-internet.herokuapp.com/status_codes/500
        webdriver().shouldHave(urlContaining("500"));

        // Возвращаемся назад
        Selenide.back();
        return this;
    }
}