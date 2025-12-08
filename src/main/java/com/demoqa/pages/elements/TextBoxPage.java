package com.demoqa.pages.elements;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.models.UserData;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TextBoxPage extends BasePage {

    // TextBoxPage не использует конструктор с URL, но должен иметь конструктор
    // без аргументов, чтобы вызвать super() по умолчанию.
    public TextBoxPage() {
        super();
    }

    private static final String URL = "https://demoqa.com/text-box";

    // --- Locators (ONLY XPath) ---
    private final SelenideElement fullNameInput = $x("//input[@id='userName']");
    private final SelenideElement emailInput = $x("//input[@id='userEmail']");
    private final SelenideElement currentAddressTextArea = $x("//textarea[@id='currentAddress']");
    private final SelenideElement permanentAddressTextArea = $x("//textarea[@id='permanentAddress']");
    private final SelenideElement submitButton = $x("//button[@id='submit']");

    // Output area locators
    private final SelenideElement outputDiv = $x("//div[@id='output']");
    private final SelenideElement outputName = $x("//p[@id='name']");
    private final SelenideElement outputEmail = $x("//p[@id='email']");
    private final SelenideElement outputCurrentAddress = $x("//p[@id='currentAddress']");
    private final SelenideElement outputPermanentAddress = $x("//p[@id='permanentAddress']");

    @Step("Open Text Box page")
    public TextBoxPage openPage() {
        // Вызываем BasePage.open(URL), который теперь возвращает BasePage.
        // openPage() возвращает TextBoxPage (ковариантный тип)
        open(URL);
        return this;
    }

    @Step("Fill form with user data: {userData}")
    public TextBoxPage fillForm(UserData userData) {
        fullNameInput.setValue(userData.getFullName());
        emailInput.setValue(userData.getEmail());
        currentAddressTextArea.setValue(userData.getCurrentAddress());
        permanentAddressTextArea.setValue(userData.getPermanentAddress());
        return this;
    }

    @Step("Click Submit button")
    public TextBoxPage clickSubmit() {
        // scrollIntoView(true) помогает, если кнопка перекрыта футером
        submitButton.scrollIntoView(true).click();
        return this;
    }

    @Step("Verify output data matches input")
    public TextBoxPage verifyOutput(UserData userData) {
        outputDiv.shouldBe(visible);

        // Проверяем, что текст содержит введенные данные
        outputName.shouldHave(text("Name:" + userData.getFullName()));
        outputEmail.shouldHave(text("Email:" + userData.getEmail()));
        outputCurrentAddress.shouldHave(text("Current Address :" + userData.getCurrentAddress()));
        outputPermanentAddress.shouldHave(text("Permananet Address :" + userData.getPermanentAddress()));

        return this;
    }
}