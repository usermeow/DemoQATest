package com.demoqa.tests.widgets;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.widgets.AccordianPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Widgets")
@Feature("Accordian Behavior")
public class AccordianTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/accordian";

    @Test(description = "Проверка раскрытия и сворачивания секций аккордеона")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет дефолтное состояние (Секция 1 открыта), затем сворачивает её и последовательно открывает Секции 2 и 3.")
    public void testAccordianNavigation() {
        new AccordianPage(PAGE_URL)
                .openPage()
                // 1. Проверяем дефолтное состояние
                .verifySection1Open()

                // 2. Кликаем по первой секции, чтобы закрыть её
                .clickSection1()
                .verifySection1Closed()

                // 3. Открываем вторую секцию
                .clickSection2()
                .verifySection2Open()

                // 4. Открываем третью секцию
                .clickSection3()
                .verifySection3Open();
    }
}