package com.demoqa.tests.forms;

import com.demoqa.config.ProjectConfig;
import com.demoqa.models.Student;
import com.demoqa.pages.forms.PracticeFormPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Epic("DemoQA Forms")
@Feature("Automation Practice Form")
public class PracticeFormTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/automation-practice-form";

    @Test(description = "Успешное заполнение и отправка полной формы регистрации")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест заполняет все поля формы (включая загрузку файла и календари), отправляет её и проверяет данные в финальной таблице.")
    public void testFillPracticeForm() throws Exception {
        // 1. Подготовка данных
        // Создаем временный файл картинки
        Path tempFile = Files.createTempFile("avatar", ".jpg");
        File picture = tempFile.toFile();

        Student student = Student.builder()
                .firstName("Sherlock")
                .lastName("Holmes")
                .email("sherlock@bakerst.com")
                .gender("Male")
                .mobile("1234567890") // Ровно 10 цифр
                .birthDay("6")
                .birthMonth("January")
                .birthYear("1900")
                .subject("Maths")     // Автокомплит сработает при вводе "Maths"
                .hobby("Music")
                .picture(picture)
                .address("221B Baker Street")
                .state("NCR")
                .city("Delhi")
                .build();

        try {
            // 2. Тест
            new PracticeFormPage(PAGE_URL)
                    .openPage()
                    .fillPersonalDetails(student) // Заполняет имя, email, телефон и адрес
                    .selectGender(student.getGender())
                    .setDateOfBirth(student.getBirthDay(), student.getBirthMonth(), student.getBirthYear())
                    .setSubject(student.getSubject())
                    .selectHobby(student.getHobby())
                    .uploadPicture(student.getPicture())
                    .setStateAndCity(student.getState(), student.getCity())
                    .submit()
                    // 3. Проверки
                    .verifyModalVisible()
                    .verifyResult("Student Name", student.getFullName())
                    .verifyResult("Student Email", student.getEmail())
                    .verifyResult("Gender", student.getGender())
                    .verifyResult("Mobile", student.getMobile())
                    .verifyResult("Date of Birth", student.getFullDateOfBirth())
                    .verifyResult("Subjects", student.getSubject())
                    .verifyResult("Hobbies", student.getHobby())
                    .verifyResult("Picture", picture.getName())
                    .verifyResult("Address", student.getAddress())
                    .verifyResult("State and City", student.getState() + " " + student.getCity());

        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}