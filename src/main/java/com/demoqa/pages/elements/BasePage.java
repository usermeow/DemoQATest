package com.demoqa.pages.elements;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public abstract class BasePage {

    // Поле для хранения URL, установленного через новый конструктор
    protected final String pageUrl;

    // КОНСТРУКТОР 1: Без аргументов. Необходим для обратной совместимости
    // с классами, которые используют open(String url) напрямую (например, TextBoxPage).
    public BasePage() {
        this.pageUrl = null;
    }

    // КОНСТРУКТОР 2: С аргументом. Используется классами, которые хотят хранить URL в объекте.
    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    // Метод для открытия страницы по явному URL.
    // Возвращает BasePage, чтобы поддерживать цепочку вызовов (Fluent Interface).
    @Step("Open URL: {url}")
    public BasePage open(String url) { // Изменен тип возврата на BasePage
        Selenide.open(url);
        removeAds();
        return this; // Возвращаем текущий объект для цепочки вызовов
    }

    // Метод для открытия страницы по URL, переданному в конструктор.
    // Возвращает BasePage, чтобы поддерживать цепочку вызовов.
    @Step("Open page using stored URL")
    public BasePage openPage() { // Изменен тип возврата на BasePage
        if (this.pageUrl == null) {
            throw new IllegalStateException("Page URL was not set in the constructor. Use open(String url) instead.");
        }
        open(this.pageUrl);
        return this; // Возвращаем текущий объект для цепочки вызовов
    }

    // Метод для скрытия рекламы (она часто мешает кликам на demoqa)
    @Step("Remove fixed ads via JS")
    public void removeAds() {
        // Удаление элементов, которые могут перекрывать кликабельные области
        Selenide.executeJavaScript(
                "const ads = document.getElementsByClassName('adsbygoogle'); " +
                        "for (let i=0; i<ads.length; i++) { ads[i].style.display='none'; } " +
                        "const fixedban = document.getElementById('fixedban'); " +
                        "if (fixedban) { fixedban.style.display='none'; } " +
                        "const footer = document.querySelector('footer'); " +
                        "if (footer) { footer.style.display='none'; }"
        );
    }
}