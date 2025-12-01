package com.demoqa.tests;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.ButtonsPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Elements")
@Feature("Buttons Interactions")
public class ButtonsTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/buttons";

    @Test(description = "Проверка двойного клика (Double Click)")
    @Severity(SeverityLevel.CRITICAL)
    public void testDoubleClick() {
        new ButtonsPage(PAGE_URL)
                .openPage()
                .performDoubleClick()
                .verifyDoubleClickMessage("You have done a double click");
    }

    @Test(description = "Проверка правого клика (Right Click)")
    @Severity(SeverityLevel.CRITICAL)
    public void testRightClick() {
        new ButtonsPage(PAGE_URL)
                .openPage()
                .performRightClick()
                .verifyRightClickMessage("You have done a right click");
    }

    @Test(description = "Проверка клика по кнопке с динамическим ID")
    @Severity(SeverityLevel.NORMAL)
    public void testDynamicClick() {
        new ButtonsPage(PAGE_URL)
                .openPage()
                .performDynamicClick()
                .verifyDynamicClickMessage("You have done a dynamic click");
    }
}