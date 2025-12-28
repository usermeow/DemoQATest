package com.demoqa.tests.widgets;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.widgets.TabsPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Widgets")
@Feature("Tabs Functionality")
public class TabsTest extends BaseTest {

    // URL страницы, которую тестируем
    private final String PAGE_URL = ProjectConfig.BASE_URL + "/tabs";

    @Test(description = "Проверка активации и содержимого вкладки 'Origin'")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет, что при клике на вкладку 'Origin', она становится активной, отображает свое содержимое и скрывает содержимое других вкладок.")
    public void testOriginTabActivationAndContent() {
        String activeTabName = "Origin";
        String inactiveTab1Name = "What";
        String inactiveTab2Name = "Use";

        // Ожидаемый текст для вкладки Origin (берем часть из контента на сайте)
        String expectedText = "Contrary to popular belief, Lorem Ipsum is not simply random text";

        new TabsPage(PAGE_URL)
                .openPage() // 1. Открываем страницу
                .clickTab(activeTabName) // 2. Кликаем на вкладку 'Origin'

                // 3. Проверка активной вкладки
                .verifyTabContentIsVisibleAndCorrect(activeTabName, expectedText)

                // 4. Проверка неактивных вкладок
                .verifyTabContentIsHidden(inactiveTab1Name)
                .verifyTabContentIsHidden(inactiveTab2Name);
    }

    @Test(description = "Проверка активации и содержимого вкладки 'Use'")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет, что при клике на вкладку 'Use', она становится активной и отображает свое содержимое.")
    public void testUseTabActivationAndContent() {
        String activeTabName = "Use";
        String inactiveTabName = "Origin"; // Проверяем только одну неактивную для краткости

        // Ожидаемый текст для вкладки Use (берем часть из контента на сайте)
        String expectedText = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout";

        new TabsPage(PAGE_URL)
                .openPage() // 1. Открываем страницу
                .clickTab(activeTabName) // 2. Кликаем на вкладку 'Use'

                // 3. Проверка активной вкладки
                .verifyTabContentIsVisibleAndCorrect(activeTabName, expectedText)

                // 4. Проверка неактивной вкладки
                .verifyTabContentIsHidden(inactiveTabName);
    }
}