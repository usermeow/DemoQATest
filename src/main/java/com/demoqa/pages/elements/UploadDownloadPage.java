package com.demoqa.pages.elements;

import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class UploadDownloadPage extends BasePage {

    // --- Локаторы (XPath) ---
    private final SelenideElement downloadButton = $x("//a[@id='downloadButton']");
    private final SelenideElement uploadInput = $x("//input[@id='uploadFile']");
    private final SelenideElement uploadedFilePath = $x("//p[@id='uploadedFilePath']");

    public UploadDownloadPage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    @Step("Открыть страницу Upload/Download")
    public UploadDownloadPage openPage() {
        super.openPage();
        return this;
    }

    // --- Методы скачивания ---

    @Step("Скачать файл по кнопке Download")
    public File downloadImage() throws FileNotFoundException {
        // ИСПРАВЛЕНИЕ 1: Используем режим FOLDER.
        // Это заставляет браузер кликнуть и сохранить файл, вместо попытки отправить HTTP-запрос.
        // Это решает ошибку "Target host is not specified".
        return downloadButton.shouldBe(visible)
                .download(DownloadOptions.using(FileDownloadMode.FOLDER));
    }

    // --- Методы загрузки ---

    // ИСПРАВЛЕНИЕ 2: Убрали {file.name}, оставили просто {file}.
    // Это решает ошибку "NoSuchFieldException: name".
    @Step("Загрузить файл: {file}")
    public UploadDownloadPage uploadFile(File file) {
        uploadInput.uploadFile(file);
        return this;
    }

    @Step("Проверить, что путь к загруженному файлу содержит: '{fileName}'")
    public UploadDownloadPage verifyUploadedFilePath(String fileName) {
        uploadedFilePath.shouldBe(visible);
        uploadedFilePath.shouldHave(text(fileName));
        return this;
    }
}