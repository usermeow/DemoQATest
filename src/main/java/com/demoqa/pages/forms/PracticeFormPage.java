package com.demoqa.pages.forms;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.models.Student;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class PracticeFormPage extends BasePage {

    // --- Локаторы (XPath) ---
    private final SelenideElement firstNameInput = $x("//input[@id='firstName']");
    private final SelenideElement lastNameInput = $x("//input[@id='lastName']");
    private final SelenideElement emailInput = $x("//input[@id='userEmail']");
    private final SelenideElement mobileInput = $x("//input[@id='userNumber']");
    private final SelenideElement addressInput = $x("//textarea[@id='currentAddress']");

    // Календарь
    private final SelenideElement dateOfBirthInput = $x("//input[@id='dateOfBirthInput']");
    private final SelenideElement monthSelect = $x("//select[@class='react-datepicker__month-select']");
    private final SelenideElement yearSelect = $x("//select[@class='react-datepicker__year-select']");

    // Auto-complete (Subjects)
    private final SelenideElement subjectsInput = $x("//input[@id='subjectsInput']");

    // Upload
    private final SelenideElement uploadPicture = $x("//input[@id='uploadPicture']");

    // State & City (React Dropdowns)
    private final SelenideElement stateInput = $x("//input[@id='react-select-3-input']");
    private final SelenideElement cityInput = $x("//input[@id='react-select-4-input']");

    private final SelenideElement submitButton = $x("//button[@id='submit']");
    private final SelenideElement modalTitle = $x("//div[@id='example-modal-sizes-title-lg']");

    public PracticeFormPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Practice Form")
    public PracticeFormPage openPage() {
        super.openPage();
        // Иногда футер перекрывает кнопку Submit, удаляем его жестко
        removeAds();
        return this;
    }

    @Step("Заполнить основные поля")
    public PracticeFormPage fillPersonalDetails(Student student) {
        firstNameInput.setValue(student.getFirstName());
        lastNameInput.setValue(student.getLastName());
        emailInput.setValue(student.getEmail());
        mobileInput.setValue(student.getMobile());
        addressInput.setValue(student.getAddress());
        return this;
    }

    @Step("Выбрать пол: {gender}")
    public PracticeFormPage selectGender(String gender) {
        // Кликаем по тексту label (Male/Female/Other)
        $x("//label[text()='" + gender + "']").click();
        return this;
    }

    @Step("Установить дату рождения: {day} {month} {year}")
    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        monthSelect.selectOption(month); // Стандартный селект
        yearSelect.selectOption(year);   // Стандартный селект

        // Клик по дню. ВАЖНО: not(contains(@class, 'outside-month')),
        // чтобы не кликнуть на 30-е число предыдущего месяца, если оно видно.
        String dayXpath = String.format(
                "//div[contains(@class, 'react-datepicker__day') and not(contains(@class, 'outside-month')) and text()='%s']",
                day
        );
        $x(dayXpath).click();
        return this;
    }

    @Step("Выбрать предмет: {subject}")
    public PracticeFormPage setSubject(String subject) {
        // Вводим текст и нажимаем Enter
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    @Step("Выбрать хобби: {hobby}")
    public PracticeFormPage selectHobby(String hobby) {
        $x("//label[text()='" + hobby + "']").click();
        return this;
    }

    @Step("Загрузить фото")
    public PracticeFormPage uploadPicture(File file) {
        uploadPicture.uploadFile(file);
        return this;
    }

    @Step("Выбрать штат: {state} и город: {city}")
    public PracticeFormPage setStateAndCity(String state, String city) {
        // Для React Select надежнее всего ввести текст и нажать Enter
        stateInput.setValue(state).pressEnter();
        cityInput.setValue(city).pressEnter();
        return this;
    }

    @Step("Отправить форму")
    public PracticeFormPage submit() {
        submitButton.scrollIntoView(true).click();
        return this;
    }

    @Step("Проверить, что модальное окно открылось")
    public PracticeFormPage verifyModalVisible() {
        modalTitle.shouldBe(visible).shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    @Step("Проверить строку в таблице результатов: '{label}' -> '{expectedValue}'")
    public PracticeFormPage verifyResult(String label, String expectedValue) {
        // Ищем строку таблицы, где в первой ячейке лейбл, а во второй проверяем текст
        $x("//td[text()='" + label + "']/following-sibling::td")
                .shouldHave(text(expectedValue));
        return this;
    }
}