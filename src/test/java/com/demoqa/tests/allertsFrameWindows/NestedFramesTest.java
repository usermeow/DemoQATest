package com.demoqa.tests.allertsFrameWindows;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.alertsFrameWindows.NestedFramesPage;
import com.demoqa.tests.elements.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Alerts, Frame & Windows")
@Feature("Nested Frames Handling")
public class NestedFramesTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/nestedframes";

    @Test(description = "Проверка навигации по вложенным фреймам")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест переключается в родительский фрейм, проверяет его текст, затем ныряет во вложенный фрейм, проверяет текст там и возвращается на главную страницу.")
    public void testNestedFrames() {
        new NestedFramesPage(PAGE_URL)
                .openPage()
                .switchToParentFrame()       // 1. Вход в Parent Frame
                .verifyParentText("Parent frame")
                .switchToChildFrame()        // 2. Вход в Child Frame (внутри Parent)
                .verifyChildText("Child Iframe")
                .switchToMainContent();      // 3. Полный выход
    }
}