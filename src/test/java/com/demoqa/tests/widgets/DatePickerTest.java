package com.demoqa.tests.widgets;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.widgets.DatePickerPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Widgets")
@Feature("Date Picker Handling")
public class DatePickerTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/date-picker";

    @Test(description = "Выбор даты в 'Select Date' (Month/Year Selects)")
    @Severity(SeverityLevel.NORMAL)
    public void testSelectDate() {
        String day = "15";
        String month = "October";
        String year = "2020";
        // Ожидаемый формат: MM/dd/yyyy
        String expectedDate = "10/15/2020";

        new DatePickerPage(PAGE_URL)
                .openPage()
                .setDate(day, month, year)
                .verifyDateValue(expectedDate);
    }

    @Test(description = "Выбор даты и времени в 'Date And Time' (Custom Dropdowns)")
    @Severity(SeverityLevel.CRITICAL)
    public void testSelectDateAndTime() {
        String day = "28";
        String month = "November";
        String year = "2024";
        String time = "14:30"; // Время в списке выбирается как "14:30"

        // Ожидаемый формат результата: November 28, 2024 2:30 PM
        // Сайт сам конвертирует 14:30 в 2:30 PM
        String expectedValue = "November 28, 2024 2:30 PM";

        new DatePickerPage(PAGE_URL)
                .openPage()
                .setDateAndTime(day, month, year, time)
                .verifyDateTimeValue(expectedValue);
    }
}