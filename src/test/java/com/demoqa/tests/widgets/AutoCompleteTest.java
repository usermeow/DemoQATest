package com.demoqa.tests.widgets;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.widgets.AutoCompletePage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Widgets")
@Feature("Auto Complete Input")
public class AutoCompleteTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/auto-complete";

    @Test(description = "Множественный выбор цветов (Multiple Input)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест вводит части названий цветов ('Re', 'Gr') и выбирает 'Red' и 'Green'. Проверяется, что оба цвета добавлены.")
    public void testMultipleColorSelection() {
        new AutoCompletePage(PAGE_URL)
                .openPage()
                // Вводим "Re" -> выбираем "Red"
                .addColor("Re", "Red")
                // Вводим "Gr" -> выбираем "Green"
                .addColor("Gr", "Green")
                // Проверяем, что оба цвета отображаются
                .verifyMultipleColors("Red", "Green");
    }

    @Test(description = "Одиночный выбор цвета (Single Input)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест вводит 'Bl', выбирает 'Blue' и проверяет, что значение поля установилось корректно.")
    public void testSingleColorSelection() {
        new AutoCompletePage(PAGE_URL)
                .openPage()
                // Вводим "Bl" -> выбираем "Blue"
                .setSingleColor("Bl", "Blue")
                // Проверяем результат
                .verifySingleColor("Blue");
    }
}