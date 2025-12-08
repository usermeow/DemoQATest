package com.demoqa.pages.alertsFrameWindows;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ModalDialogsPage extends BasePage {

    // --- Локаторы (XPath) ---

    // Кнопки вызова модалок
    private final SelenideElement smallModalButton = $x("//button[@id='showSmallModal']");
    private final SelenideElement largeModalButton = $x("//button[@id='showLargeModal']");

    // --- Элементы Маленького модального окна ---
    private final SelenideElement smallModalHeader = $x("//div[@id='example-modal-sizes-title-sm']");
    private final SelenideElement smallModalBody = $x("//div[contains(@class, 'modal-body')]");
    private final SelenideElement closeSmallModalButton = $x("//button[@id='closeSmallModal']");

    // --- Элементы Большого модального окна ---
    private final SelenideElement largeModalHeader = $x("//div[@id='example-modal-sizes-title-lg']");
    private final SelenideElement largeModalBody = $x("//div[contains(@class, 'modal-body')]//p");
    private final SelenideElement closeLargeModalButton = $x("//button[@id='closeLargeModal']");

    // Общий контейнер модального окна (для проверки исчезновения)
    private final SelenideElement modalContent = $x("//div[@class='modal-content']");

    public ModalDialogsPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Modal Dialogs")
    public ModalDialogsPage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы для Small Modal ---

    @Step("Нажать кнопку 'Small modal'")
    public ModalDialogsPage openSmallModal() {
        smallModalButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить заголовок и текст маленького модального окна")
    public ModalDialogsPage verifySmallModalContent(String expectedTitle, String expectedBodyPart) {
        modalContent.shouldBe(visible);
        smallModalHeader.shouldHave(text(expectedTitle));
        smallModalBody.shouldHave(text(expectedBodyPart));
        return this;
    }

    @Step("Закрыть маленькое модальное окно")
    public ModalDialogsPage closeSmallModal() {
        closeSmallModalButton.click();
        return this;
    }

    // --- Методы для Large Modal ---

    @Step("Нажать кнопку 'Large modal'")
    public ModalDialogsPage openLargeModal() {
        largeModalButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить заголовок и текст большого модального окна")
    public ModalDialogsPage verifyLargeModalContent(String expectedTitle, String expectedBodyPart) {
        modalContent.shouldBe(visible);
        largeModalHeader.shouldHave(text(expectedTitle));
        largeModalBody.shouldHave(text(expectedBodyPart));
        return this;
    }

    @Step("Закрыть большое модальное окно")
    public ModalDialogsPage closeLargeModal() {
        closeLargeModalButton.click();
        return this;
    }

    @Step("Проверить, что модальное окно закрылось (исчезло)")
    public ModalDialogsPage verifyModalClosed() {
        modalContent.should(disappear);
        return this;
    }
}