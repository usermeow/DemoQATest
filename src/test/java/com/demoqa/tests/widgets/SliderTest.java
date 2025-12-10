package com.demoqa.tests.widgets;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.widgets.SliderPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Widgets")
@Feature("Slider Interaction")
public class SliderTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/slider";

    @Test(description = "Проверка перемещения слайдера")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест устанавливает различные значения слайдера (граничные и средние) и проверяет поле отображения результата.")
    public void testSliderMovement() {
        SliderPage page = new SliderPage(PAGE_URL);

        page.openPage();

        // 1. Проверка дефолтного значения (обычно 25)
        page.verifySliderValue(25);

        // 2. Перемещение на значение 85
        page.moveSliderTo(85)
                .verifySliderValue(85);

        // 3. Перемещение на минимальное значение (0)
        page.moveSliderTo(0)
                .verifySliderValue(0);

        // 4. Перемещение на максимальное значение (100)
        page.moveSliderTo(100)
                .verifySliderValue(100);
    }
}