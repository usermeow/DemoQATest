package com.demoqa.tests.widgets;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.widgets.SelectMenuPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Widgets")
@Feature("Select Menu Interactions")
public class SelectMenuTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/select-menu";

    @Test(description = "Проверка React Select (Select Value и Select One)")
    @Severity(SeverityLevel.CRITICAL)
    public void testReactSelects() {
        new SelectMenuPage(PAGE_URL)
                .openPage()
                // 1. Тест 'Select Value' (выбор опции из группы)
                .selectGroupOption("Group 1, option 2")
                .verifySelectValue("Group 1, option 2")

                // 2. Тест 'Select One' (выбор обращения)
                .selectOneOption("Dr.")
                .verifySelectOne("Dr.");
    }

    @Test(description = "Проверка стандартного HTML Select (Old Style)")
    @Severity(SeverityLevel.NORMAL)
    public void testOldStyleSelect() {
        new SelectMenuPage(PAGE_URL)
                .openPage()
                .selectOldStyleColor("Purple")
                .verifyOldStyleColor("Purple");
    }

    @Test(description = "Проверка мультиселекта (React Multiselect)")
    @Severity(SeverityLevel.NORMAL)
    public void testMultiselectDropdown() {
        new SelectMenuPage(PAGE_URL)
                .openPage()
                .selectMultiColors("Green", "Blue", "Black")
                .verifyMultiSelectColors("Green", "Blue", "Black");
    }

    @Test(description = "Проверка стандартного мультиселекта (Standard Multi Select)")
    @Severity(SeverityLevel.NORMAL)
    public void testStandardMultiSelect() {
        new SelectMenuPage(PAGE_URL)
                .openPage()
                .selectStandardCars("Volvo", "Audi");
        // Визуальной проверки на странице нет (текст не меняется),
        // но selectOption() выбросит ошибку, если опции не будут найдены.
    }
}