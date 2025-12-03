package com.demoqa.tests.elements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setUp() {
        // Настройки Selenide
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager"; // Ускоряет тесты, не ждет полной загрузки картинок
        // Configuration.headless = true; // Раскомментировать для запуска без UI (на CI/CD)

        // Интеграция Allure и Selenide (для скриншотов и степов)
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(false));
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}