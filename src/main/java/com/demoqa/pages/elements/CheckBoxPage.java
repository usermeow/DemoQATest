package com.demoqa.pages.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckBoxPage extends BasePage {

    // Селекторы элементов
    private final SelenideElement expandAllButton = $(".rct-option-expand-all");
    private final ElementsCollection resultElements = $$("#result .text-success");

    // Конструктор, вызывающий конструктор BasePage с pageUrl
    public CheckBoxPage(String pageUrl) {
        super(pageUrl);
    }

    // openPage() наследуется от BasePage и вызывает super.open(this.pageUrl),
    // а затем возвращает this (BasePage).
    // Мы переопределяем его, чтобы вернуть CheckBoxPage (ковариантный тип).
    @Step("Open CheckBox page")
    public CheckBoxPage openPage() {
        super.openPage(); // Открываем страницу, используя URL из конструктора
        return this;
    }

    @Step("Раскрыть все узлы дерева")
    public CheckBoxPage expandAll() {
        expandAllButton.shouldBe(visible).click();
        return this;
    }

    @Step("Выбрать узел '{nodeName}'")
    public CheckBoxPage selectNode(String nodeName) {
        // Находим элемент с текстом nodeName, затем находим его родителя (span)
        // и кликаем на соседний чекбокс, используя XPath
        String xpath = String.format("//span[text()='%s']/preceding-sibling::span[@class='rct-checkbox']", nodeName);
        $(By.xpath(xpath)).shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что в результате отображается текст '{expectedText}'")
    public CheckBoxPage verifyOutputResult(String expectedText) {
        $("#result").shouldBe(visible);
        // Проверяем, что первый (и единственный) элемент результата содержит ожидаемый текст
        resultElements.get(0).text().contains(expectedText);
        return this;
    }

    @Step("Проверить, что выбраны следующие элементы: {expectedItems}")
    public CheckBoxPage verifySelectedItems(String... expectedItems) {
        $("#result").shouldBe(visible);
        // Проверяем, что количество и текст выбранных элементов совпадают
        resultElements.shouldHave(texts(Arrays.asList(expectedItems)));
        return this;
    }
}