package com.demoqa.tests.elements;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.elements.UploadDownloadPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Epic("DemoQA Elements")
@Feature("File Upload and Download")
public class UploadDownloadTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/upload-download";

    @Test(description = "Проверка скачивания файла")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест нажимает кнопку Download, скачивает файл и проверяет его существование и размер.")
    public void testDownloadFile() throws IOException {
        UploadDownloadPage page = new UploadDownloadPage(PAGE_URL);

        page.openPage();

        // Действие: Скачиваем файл
        File downloadedFile = page.downloadImage();

        // Проверки (через Java IO или TestNG Assert, т.к. файл у нас уже на диске)
        Assert.assertTrue(downloadedFile.exists(), "Файл не был скачан");
        Assert.assertTrue(downloadedFile.length() > 0, "Скачанный файл пустой");
        Assert.assertTrue(downloadedFile.getName().contains("sampleFile"), "Имя файла некорректно");
    }

    @Test(description = "Проверка загрузки файла (Upload)")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест создает временный файл, загружает его через форму и проверяет отображение пути на сайте.")
    public void testUploadFile() throws IOException {
        // 1. Подготовка: Создаем временный файл для теста
        // Он создается в папке temp системы и удалится после завершения Java процесса
        Path tempFile = Files.createTempFile("automation_test_", ".txt");
        File fileToUpload = tempFile.toFile();
        String fileName = fileToUpload.getName();

        try {
            // 2. Тест
            new UploadDownloadPage(PAGE_URL)
                    .openPage()
                    .uploadFile(fileToUpload)       // Загружаем созданный файл
                    .verifyUploadedFilePath(fileName); // Проверяем, что сайт увидел имя файла

        } finally {
            // Очистка: удаляем временный файл (хороший тон)
            Files.deleteIfExists(tempFile);
        }
    }
}