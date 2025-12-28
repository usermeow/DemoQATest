package com.demoqa.tests.widgets;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Widgets")
@Feature("Menu Navigation")
public class MenuTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/menu";

    @Test(description = "Проверка навигации по многоуровневому меню")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест наводит курсор на 'Main Item 2', затем на 'SUB SUB LIST »' и проверяет, что самый глубокий уровень вложенности становится видимым.")
    public void testNestedMenuHover() {
        new MenuPage(PAGE_URL)
                .openPage()
                // 1. Наводим на пункт 2
                .hoverMainItem2()
                .verifySubItemVisible() // Проверяем, что выпало первое меню

                // 2. Наводим на вложенный список
                .hoverSubSubList()

                // 3. Проверяем, что выпало второе меню (3-й уровень)
                .verifySubSubItemsVisible();
    }
}