package com.demoqa.tests.elements;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.elements.CheckBoxPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Elements")
@Feature("Check Box Functionality")
public class CheckBoxTest extends BaseTest {

    // Формируем полный URL страницы
    private final String PAGE_URL = ProjectConfig.BASE_URL + "/checkbox";

    @Test(description = "Успешный выбор вложенного файла (Word File.doc)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет возможность раскрыть дерево папок и выбрать конкретный файл. Ожидается, что результат выбора отобразится корректно.")
    public void testSelectNestedNode() {
        // Название узла в дереве (как мы видим его на экране)
        String nodeToSelect = "Word File.doc";
        // Ожидаемый текст в результате (сайт преобразует "Word File.doc" в "wordFile")
        String expectedOutput = "wordFile";

        new CheckBoxPage(PAGE_URL) // Передаем URL в конструктор
                .openPage()           // 1. Открываем страницу (используя openPage())
                .expandAll()          // 2. Раскрываем все папки, чтобы элемент стал видимым
                .selectNode(nodeToSelect) // 3. Кликаем по нужному файлу
                .verifyOutputResult(expectedOutput); // 4. Проверяем, что внизу появился текст "wordFile"
    }

    @Test(description = "Выбор корневой папки 'Home' выбирает все 17 элементов")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет, что при выборе родительского чекбокса 'Home' автоматически выбираются все дочерние элементы (17 штук), включая вложенные папки.")
    public void testSelectHomeNode() {
        new CheckBoxPage(PAGE_URL) // Передаем URL в конструктор
                .openPage()
                .selectNode("Home") // Home виден сразу
                // Проверяем массив ожидаемых значений (сайт возвращает 17 элементов в нижнем регистре)
                .verifySelectedItems(
                        "home",
                        "desktop",
                        "notes",
                        "commands",
                        "documents",
                        "workspace", // Скрытая папка
                        "react",     // Скрытая папка
                        "angular",   // Скрытая папка
                        "veu",       // Скрытая папка
                        "office",    // Скрытая папка
                        "public",    // Скрытая папка
                        "private",   // Скрытая папка
                        "classified",// Скрытая папка
                        "general",   // Скрытая папка
                        "downloads",
                        "wordFile",
                        "excelFile"
                );
    }
}