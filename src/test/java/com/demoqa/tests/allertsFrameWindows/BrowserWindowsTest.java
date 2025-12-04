package com.demoqa.tests.allertsFrameWindows;


import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.alertsFrameWindows.BrowserWindowsPage;
import com.demoqa.tests.elements.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Alerts, Frame & Windows")
@Feature("Browser Windows Handling")
public class BrowserWindowsTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/browser-windows";
    private final String EXPECTED_TEXT = "This is a sample page";

    @Test(description = "Проверка открытия новой вкладки (New Tab)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест нажимает кнопку New Tab, переключает фокус на новую вкладку, проверяет заголовок и закрывает её.")
    public void testNewTab() {
        new BrowserWindowsPage(PAGE_URL)
                .openPage()
                .checkNewTab(EXPECTED_TEXT);
    }

    @Test(description = "Проверка открытия нового окна (New Window)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест нажимает кнопку New Window, переключает фокус на новое окно, проверяет заголовок и закрывает его.")
    public void testNewWindow() {
        new BrowserWindowsPage(PAGE_URL)
                .openPage()
                .checkNewWindow(EXPECTED_TEXT);
    }
}