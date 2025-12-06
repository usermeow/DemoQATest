package com.demoqa.tests.allertsFrameWindows;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.tests.elements.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Alerts, Frame & Windows")
@Feature("Frames Handling")
public class FramesTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/frames";
    private final String EXPECTED_TEXT = "This is a sample page";

    @Test(description = "Проверка текста внутри фреймов (большого и маленького)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест переключается в первый фрейм, проверяет текст, возвращается, затем переключается во второй фрейм и снова проверяет текст.")
    public void testFramesContent() {
        FramesPage page = new FramesPage(PAGE_URL);

        page.openPage();

        // 1. Проверка большого фрейма
        page.switchToBigFrame()
                .verifyFrameText(EXPECTED_TEXT)
                .switchToMainContent(); // Обязательно возвращаемся!

        // 2. Проверка маленького фрейма
        page.switchToSmallFrame()
                .verifyFrameText(EXPECTED_TEXT)
                .switchToMainContent();
    }
}