package com.demoqa.tests.widgets;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.widgets.ToolTipsPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Widgets")
@Feature("Tool Tips Verification")
public class ToolTipsTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/tool-tips";

    @Test(description = "Проверка тултипа при наведении на кнопку")
    @Severity(SeverityLevel.NORMAL)
    public void testButtonTooltip() {
        new ToolTipsPage(PAGE_URL)
                .openPage()
                .hoverOverButton()
                .verifyTooltipText("You hovered over the Button");
    }

    @Test(description = "Проверка тултипа при наведении на текстовое поле")
    @Severity(SeverityLevel.NORMAL)
    public void testInputTooltip() {
        new ToolTipsPage(PAGE_URL)
                .openPage()
                .hoverOverInput()
                .verifyTooltipText("You hovered over the text field");
    }

    @Test(description = "Проверка тултипа при наведении на ссылку 'Contrary'")
    @Severity(SeverityLevel.NORMAL)
    public void testLinkTooltip() {
        new ToolTipsPage(PAGE_URL)
                .openPage()
                .hoverOverContraryLink()
                .verifyTooltipText("You hovered over the Contrary");
    }
}