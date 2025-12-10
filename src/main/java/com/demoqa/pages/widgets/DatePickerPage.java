package com.demoqa.pages.widgets;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DatePickerPage extends BasePage {

    // --- Локаторы: Select Date (Левый календарь) ---
    private final SelenideElement dateInput = $x("//input[@id='datePickerMonthYearInput']");
    private final SelenideElement monthSelect = $x("//select[@class='react-datepicker__month-select']");
    private final SelenideElement yearSelect = $x("//select[@class='react-datepicker__year-select']");

    // --- Локаторы: Date And Time (Правый календарь) ---
    private final SelenideElement dateTimeInput = $x("//input[@id='dateAndTimePickerInput']");

    // Кастомные дропдауны для месяца и года (нужно кликнуть, чтобы раскрыть)
    private final SelenideElement monthDropdown = $x("//div[@class='react-datepicker__month-read-view']");
    private final SelenideElement yearDropdown = $x("//div[@class='react-datepicker__year-read-view']");

    // Списки опций для месяца и года (ищем по тексту внутри контейнера)
    // Используем динамический XPath в методах

    public DatePickerPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Date Picker")
    public DatePickerPage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы для Select Date ---

    @Step("Установить дату (Select Date): {day} {month} {year}")
    public DatePickerPage setDate(String day, String month, String year) {
        dateInput.click();

        // В этом календаре используются стандартные HTML select
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);

        // Выбор дня. Исключаем дни из соседних месяцев (.outside-month)
        $x(String.format(
                "//div[contains(@class, 'react-datepicker__day') and not(contains(@class, 'outside-month')) and text()='%s']",
                day
        )).click();

        return this;
    }

    @Step("Проверить значение в поле Select Date: '{expectedDate}'")
    public DatePickerPage verifyDateValue(String expectedDate) {
        // Формат даты на сайте: MM/dd/yyyy (например, 10/15/2020)
        dateInput.shouldHave(value(expectedDate));
        return this;
    }

    // --- Методы для Date And Time ---

    @Step("Установить дату и время: {day} {month} {year} в {time}")
    public DatePickerPage setDateAndTime(String day, String month, String year, String time) {
        dateTimeInput.click();

        // 1. Выбор месяца (Кастомный дропдаун)
        monthDropdown.click();
        $x("//div[@class='react-datepicker__month-option' and text()='" + month + "']").click();

        // 2. Выбор года (Кастомный дропдаун)
        yearDropdown.click();
        // В списке годов нужно найти нужный. Иногда нужно скроллить, если год далеко.
        // Для простоты ищем видимый или скроллим к нему.
        SelenideElement yearOption = $x("//div[@class='react-datepicker__year-option' and text()='" + year + "']");
        yearOption.scrollIntoView(true).click();

        // 3. Выбор дня
        $x(String.format(
                "//div[contains(@class, 'react-datepicker__day') and not(contains(@class, 'outside-month')) and text()='%s']",
                day
        )).click();

        // 4. Выбор времени (Список справа)
        $x("//li[contains(@class, 'react-datepicker__time-list-item') and text()='" + time + "']").click();

        return this;
    }

    @Step("Проверить значение в поле Date And Time: '{expectedValue}'")
    public DatePickerPage verifyDateTimeValue(String expectedValue) {
        // Формат даты: Month DD, YYYY h:mm AM/PM (например, October 15, 2020 1:45 PM)
        dateTimeInput.shouldHave(value(expectedValue));
        return this;
    }
}