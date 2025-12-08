package com.demoqa.tests.allertsFrameWindows;

import com.demoqa.config.ProjectConfig;
import com.demoqa.pages.alertsFrameWindows.ModalDialogsPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Alerts, Frame & Windows")
@Feature("Modal Dialogs")
public class ModalDialogsTest extends BaseTest {

    private final String PAGE_URL = ProjectConfig.BASE_URL + "/modal-dialogs";

    @Test(description = "Проверка работы маленького модального окна")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест открывает Small Modal, проверяет его заголовок и содержимое, затем закрывает и убеждается, что окно исчезло.")
    public void testSmallModal() {
        new ModalDialogsPage(PAGE_URL)
                .openPage()
                .openSmallModal()
                .verifySmallModalContent("Small Modal", "This is a small modal")
                .closeSmallModal()
                .verifyModalClosed();
    }

    @Test(description = "Проверка работы большого модального окна")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест открывает Large Modal, проверяет его заголовок и часть длинного текста, затем закрывает окно.")
    public void testLargeModal() {
        // Часть текста Lorem Ipsum для проверки
        String expectedTextPart = "Lorem Ipsum is simply dummy text";

        new ModalDialogsPage(PAGE_URL)
                .openPage()
                .openLargeModal()
                .verifyLargeModalContent("Large Modal", expectedTextPart)
                .closeLargeModal()
                .verifyModalClosed();
    }
}