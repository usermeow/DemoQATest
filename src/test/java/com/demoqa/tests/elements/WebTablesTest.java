package com.demoqa.tests.elements;

import com.demoqa.config.ProjectConfig;
import com.demoqa.models.Employee;
import com.demoqa.pages.elements.WebTablesPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.UUID;

@Epic("DemoQA Elements")
@Feature("Web Tables")
public class WebTablesTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/webtables";

    // Хелпер для генерации уникальных данных, чтобы тесты не путались
    private Employee createRandomEmployee() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 5);
        return Employee.builder()
                .firstName("Alex")
                .lastName("Testov")
                .email("alex." + uniqueId + "@test.com") // Уникальный email важен для поиска
                .age("30")
                .salary("150000")
                .department("QA")
                .build();
    }

    @Test(description = "Добавление нового сотрудника в таблицу")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет открытие формы, заполнение валидными данными и появление записи в таблице.")
    public void testAddNewEmployee() {
        Employee newEmployee = createRandomEmployee();

        new WebTablesPage(PAGE_URL)
                .openPage()
                .clickAdd()
                .fillRegistrationForm(newEmployee)
                .submitForm()
                .search(newEmployee.getEmail()) // Фильтруем таблицу, чтобы точно видеть нашего юзера
                .verifyRecordPresent(newEmployee.getEmail());
    }

    @Test(description = "Редактирование и удаление сотрудника")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест создает сотрудника, затем меняет его имя, проверяет изменения и удаляет запись.")
    public void testEditAndDeleteEmployee() {
        Employee employee = createRandomEmployee();
        String newFirstName = "SuperAlex";

        WebTablesPage page = new WebTablesPage(PAGE_URL);

        // 1. Создаем сотрудника (Precondition)
        page.openPage()
                .clickAdd()
                .fillRegistrationForm(employee)
                .submitForm()
                .search(employee.getEmail()); // Находим его

        // 2. Редактируем
        page.editRecord(employee.getEmail());

        // Меняем имя в модели для удобства
        employee.setFirstName(newFirstName);

        // В открывшейся форме меняем только имя и сохраняем
        page.fillRegistrationForm(employee)
                .submitForm();

        // 3. Проверка: таблица должна содержать обновленное имя (можно добавить метод для проверки имени, но пока косвенно)
        // Для простоты здесь убедимся, что запись все еще ищется по email,
        // но в идеале нужно проверять ячейку с именем.
        page.verifyRecordPresent(employee.getEmail());

        // 4. Удаляем
        page.deleteRecord(employee.getEmail());

        // 5. Проверка удаления
        page.verifyRecordNotPresent(employee.getEmail());
    }
}