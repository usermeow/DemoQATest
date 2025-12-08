package com.demoqa.pages.widgets;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class AccordianPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Заголовки секций (на них нужно кликать)
    private final SelenideElement section1Heading = $x("//div[@id='section1Heading']");
    private final SelenideElement section2Heading = $x("//div[@id='section2Heading']");
    private final SelenideElement section3Heading = $x("//div[@id='section3Heading']");

    // Блоки с контентом (проверяем их видимость)
    // У этих блоков есть ID, например section1Content
    private final SelenideElement section1Content = $x("//div[@id='section1Content']");
    private final SelenideElement section2Content = $x("//div[@id='section2Content']");
    private final SelenideElement section3Content = $x("//div[@id='section3Content']");

    public AccordianPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Accordian")
    public AccordianPage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы для Секции 1 (What is Lorem Ipsum?) ---

    @Step("Кликнуть на заголовок Секции 1")
    public AccordianPage clickSection1() {
        // scrollTo() полезен, если футер перекрывает элемент внизу страницы
        section1Heading.scrollTo().click();
        return this;
    }

    @Step("Проверить, что Секция 1 раскрыта (текст виден)")
    public AccordianPage verifySection1Open() {
        section1Content.shouldBe(visible).shouldHave(text("Lorem Ipsum is simply dummy text"));
        return this;
    }

    @Step("Проверить, что Секция 1 свернута (текст скрыт)")
    public AccordianPage verifySection1Closed() {
        section1Content.shouldNotBe(visible);
        return this;
    }

    // --- Методы для Секции 2 (Where does it come from?) ---

    @Step("Кликнуть на заголовок Секции 2")
    public AccordianPage clickSection2() {
        section2Heading.scrollTo().click();
        return this;
    }

    @Step("Проверить, что Секция 2 раскрыта")
    public AccordianPage verifySection2Open() {
        section2Content.shouldBe(visible).shouldHave(text("Contrary to popular belief"));
        return this;
    }

    // --- Методы для Секции 3 (Why do we use it?) ---

    @Step("Кликнуть на заголовок Секции 3")
    public AccordianPage clickSection3() {
        section3Heading.scrollTo().click();
        return this;
    }

    @Step("Проверить, что Секция 3 раскрыта")
    public AccordianPage verifySection3Open() {
        section3Content.shouldBe(visible).shouldHave(text("It is a long established fact"));
        return this;
    }
}