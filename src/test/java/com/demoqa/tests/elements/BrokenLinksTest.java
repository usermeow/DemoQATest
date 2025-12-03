package com.demoqa.tests.elements;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.elements.BrokenLinksPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Elements")
@Feature("Broken Links and Images")
public class BrokenLinksTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/broken";

    @Test(description = "Проверка отображения картинок (Валидная vs Сломанная)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет атрибут naturalWidth у картинок через JavaScript, чтобы определить, загрузились ли они.")
    public void testImagesStatus() {
        new BrokenLinksPage(PAGE_URL)
                .openPage()
                .verifyValidImage()  // Ожидаем width > 0
                .verifyBrokenImage(); // Ожидаем width == 0
    }

    @Test(description = "Проверка переходов по ссылкам (Валидная vs Сломанная)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест кликает по ссылкам и проверяет, что URL меняется на ожидаемый (главная страница или страница 500).")
    public void testLinksNavigation() {
        new BrokenLinksPage(PAGE_URL)
                .openPage()
                .clickValidLinkAndVerify()  // Должен перейти на главную
                .clickBrokenLinkAndVerify(); // Должен перейти на 500 Error
    }
}