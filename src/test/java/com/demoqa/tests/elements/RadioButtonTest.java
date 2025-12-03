package com.demoqa.tests.elements;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.elements.RadioButtonPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Elements")
@Feature("Radio Button")
public class RadioButtonTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/radio-button";

    @Test(description = "Выбор радио-кнопки 'Yes'")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет успешный выбор опции Yes и корректное отображение результата.")
    public void testSelectYesOption() {
        new RadioButtonPage(PAGE_URL)
                .openPage()
                .selectYes()
                .verifyOutput("Yes");
    }

    @Test(description = "Выбор радио-кнопки 'Impressive'")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет успешный выбор опции Impressive.")
    public void testSelectImpressiveOption() {
        new RadioButtonPage(PAGE_URL)
                .openPage()
                .selectImpressive()
                .verifyOutput("Impressive");
    }

    @Test(description = "Проверка недоступности кнопки 'No'")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет, что радио-кнопка No заблокирована (disabled) и не может быть выбрана.")
    public void testNoOptionIsDisabled() {
        new RadioButtonPage(PAGE_URL)
                .openPage()
                .verifyNoIsDisabled(); // Проверяем, что она серая/неактивная
    }
}