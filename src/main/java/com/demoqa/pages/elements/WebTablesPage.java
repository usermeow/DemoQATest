package com.demoqa.pages.elements;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.models.Employee;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class WebTablesPage extends BasePage {

    // --- Локаторы (XPath) ---
    private final SelenideElement addButton = $x("//button[@id='addNewRecordButton']");
    private final SelenideElement searchInput = $x("//input[@id='searchBox']");

    // Локаторы модального окна (Registration Form)
    private final SelenideElement modalHeader = $x("//div[@id='registration-form-modal']");
    private final SelenideElement firstNameInput = $x("//input[@id='firstName']");
    private final SelenideElement lastNameInput = $x("//input[@id='lastName']");
    private final SelenideElement emailInput = $x("//input[@id='userEmail']");
    private final SelenideElement ageInput = $x("//input[@id='age']");
    private final SelenideElement salaryInput = $x("//input[@id='salary']");
    private final SelenideElement departmentInput = $x("//input[@id='department']");
    private final SelenideElement submitButton = $x("//button[@id='submit']");

    public WebTablesPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Web Tables")
    public WebTablesPage openPage() {
        super.openPage();
        return this;
    }

    @Step("Нажать кнопку Add для добавления новой записи")
    public WebTablesPage clickAdd() {
        addButton.shouldBe(visible).click();
        modalHeader.shouldBe(visible); // Убеждаемся, что модалка открылась
        return this;
    }

    @Step("Заполнить форму регистрации сотрудника")
    public WebTablesPage fillRegistrationForm(Employee employee) {
        firstNameInput.setValue(employee.getFirstName());
        lastNameInput.setValue(employee.getLastName());
        emailInput.setValue(employee.getEmail());
        ageInput.setValue(employee.getAge());
        salaryInput.setValue(employee.getSalary());
        departmentInput.setValue(employee.getDepartment());
        return this;
    }

    @Step("Сохранить форму")
    public WebTablesPage submitForm() {
        submitButton.click();
        modalHeader.should(disappear); // Ждем закрытия модалки
        return this;
    }

    @Step("Искать сотрудника по email: {query}")
    public WebTablesPage search(String query) {
        searchInput.setValue(query);
        return this;
    }

    @Step("Редактировать запись с email: {email}")
    public WebTablesPage editRecord(String email) {
        // Сложный XPath: ищем строку (div role='row'), которая содержит div с текстом email,
        // и внутри этой строки ищем кнопку редактирования (span title='Edit')
        String xpath = String.format("//div[@role='row' and .//div[text()='%s']]//span[@title='Edit']", email);
        $x(xpath).shouldBe(visible).click();
        return this;
    }

    @Step("Удалить запись с email: {email}")
    public WebTablesPage deleteRecord(String email) {
        // Аналогично, ищем кнопку Delete в строке с нужным email
        String xpath = String.format("//div[@role='row' and .//div[text()='%s']]//span[@title='Delete']", email);
        $x(xpath).shouldBe(visible).click();
        return this;
    }

    @Step("Проверить наличие записи в таблице: {email}")
    public WebTablesPage verifyRecordPresent(String email) {
        // Проверяем, что ячейка с таким email существует и видима
        $x("//div[@role='gridcell' and text()='" + email + "']").shouldBe(visible);
        return this;
    }

    @Step("Проверить отсутствие записи в таблице: {email}")
    public WebTablesPage verifyRecordNotPresent(String email) {
        // Проверяем, что элемент не существует
        $x("//div[@role='gridcell' and text()='" + email + "']").shouldNot(exist);
        return this;
    }
}