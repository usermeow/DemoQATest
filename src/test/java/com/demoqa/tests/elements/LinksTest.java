package com.demoqa.tests.elements;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.elements.LinksPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Elements")
@Feature("Links Handling")
public class LinksTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/links";

    @Test(description = "Проверка ссылки, открывающей новую вкладку")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест кликает по ссылке Home, переключается на новую вкладку, проверяет URL и закрывает вкладку.")
    public void testNewTabLink() {
        new LinksPage(PAGE_URL)
                .openPage()
                .clickHomeLinkAndSwitchTab()
                .verifyCurrentUrlAndContent("https://demoqa.com/")
                .closeTabAndReturn(); // Важно вернуться, чтобы не сломать драйвер, если тесты идут подряд
    }

    @Test(description = "Проверка API ссылок (Created, Not Found)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест кликает по ссылкам, имитирующим API вызовы, и проверяет текст ответа на странице.")
    public void testApiLinks() {
        LinksPage page = new LinksPage(PAGE_URL);

        page.openPage();

        // Проверка статуса 201
        page.clickCreatedLink()
                .verifyResponse("201", "Created");

        // Проверка статуса 404
        page.clickNotFoundLink()
                .verifyResponse("404", "Not Found");
    }
}