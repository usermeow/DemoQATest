package com.demoqa.tests.elements;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.elements.DynamicPropertiesPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Elements")
@Feature("Dynamic Properties (Waits)")
public class DynamicPropertiesTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/dynamic-properties";

    @Test(description = "Проверка появления кнопки (Visible After)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест ждет (до 6 секунд), пока кнопка не станет видимой на странице.")
    public void testButtonVisibility() {
        new DynamicPropertiesPage(PAGE_URL)
                .openPage()
                .verifyButtonBecomesVisible();
    }

    @Test(description = "Проверка активации кнопки (Enable After)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет, что изначально заблокированная кнопка становится активной через 5 секунд.")
    public void testButtonEnableState() {
        new DynamicPropertiesPage(PAGE_URL)
                .openPage()
                .verifyButtonBecomesEnabled();
    }

    @Test(description = "Проверка изменения цвета кнопки")
    @Severity(SeverityLevel.MINOR)
    @Description("Тест проверяет, что кнопка получает CSS класс 'text-danger' (красный цвет) через 5 секунд.")
    public void testButtonColorChange() {
        new DynamicPropertiesPage(PAGE_URL)
                .openPage()
                .verifyButtonChangesColor();
    }
}