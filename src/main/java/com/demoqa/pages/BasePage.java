package com.demoqa.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public abstract class BasePage {

    @Step("Open URL: {url}")
    public void open(String url) {
        Selenide.open(url);
    }

    // Метод для скрытия рекламы (она часто мешает кликам на demoqa)
    @Step("Remove fixed ads via JS")
    public void removeAds() {
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