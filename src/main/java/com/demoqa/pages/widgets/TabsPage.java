package com.demoqa.pages.widgets;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object Model для страницы 'Tabs' (Вкладки).
 */
public class TabsPage extends BasePage {

    // --- Локаторы вкладок ---
    private final SelenideElement whatTab = $("#demo-tab-What");
    private final SelenideElement originTab = $("#demo-tab-Origin");
    private final SelenideElement useTab = $("#demo-tab-Use");

    // --- Локаторы содержимого вкладок (Content Panes) ---
    // Content panes имеют атрибут 'aria-hidden'='false', когда активны, и 'true', когда неактивны.
    private final SelenideElement whatContent = $("#demo-tabpane-What");
    private final SelenideElement originContent = $("#demo-tabpane-Origin");
    private final SelenideElement useContent = $("#demo-tabpane-Use");

    // Конструктор, принимающий URL страницы
    public TabsPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Tabs")
    public TabsPage openPage() {
        super.openPage();
        return this;
    }

    /**
     * Возвращает SelenideElement для указанной вкладки по ее названию.
     * @param tabName Имя вкладки (What, Origin, Use).
     * @return SelenideElement вкладки.
     */
    private SelenideElement getTabElement(String tabName) {
        return $(By.id("demo-tab-" + tabName));
    }

    /**
     * Возвращает SelenideElement для панели содержимого по ее названию.
     * @param tabName Имя вкладки (What, Origin, Use).
     * @return SelenideElement панели содержимого.
     */
    private SelenideElement getContentElement(String tabName) {
        return $(By.id("demo-tabpane-" + tabName));
    }

    @Step("Кликнуть по вкладке '{tabName}'")
    public TabsPage clickTab(String tabName) {
        SelenideElement tab = getTabElement(tabName);
        // Проверяем, что вкладка активна, прежде чем кликать (если она уже не активна)
        if (!tab.getAttribute("aria-selected").equals("true")) {
            tab.shouldBe(visible, Duration.ofSeconds(5)).click();
        }
        return this;
    }

    @Step("Проверить, что вкладка '{tabName}' активна и отображает ожидаемый текст")
    public TabsPage verifyTabContentIsVisibleAndCorrect(String tabName, String expectedText) {
        SelenideElement tab = getTabElement(tabName);
        SelenideElement content = getContentElement(tabName);

        // 1. Проверяем, что сама вкладка помечена как выбранная (активная)
        tab.shouldHave(attribute("aria-selected", "true"));

        // 2. Проверяем, что контент-панель видима
        content.shouldBe(visible);

        // 3. Проверяем, что контент-панель не скрыта (атрибут aria-hidden='false')
        content.shouldHave(attribute("aria-hidden", "false"));

        // 4. Проверяем, что содержимое соответствует ожидаемому тексту
        content.shouldHave(text(expectedText));

        return this;
    }

    @Step("Проверить, что вкладка '{tabName}' неактивна и скрыта")
    public TabsPage verifyTabContentIsHidden(String tabName) {
        SelenideElement tab = getTabElement(tabName);
        SelenideElement content = getContentElement(tabName);

        // 1. Проверяем, что вкладка не помечена как выбранная (неактивная)
        tab.shouldHave(attribute("aria-selected", "false"));

        // 2. Проверяем, что контент-панель невидима
        content.shouldNotBe(visible);

        // 3. Проверяем, что контент-панель скрыта (атрибут aria-hidden='true')
        content.shouldHave(attribute("aria-hidden", "true"));

        return this;
    }
}