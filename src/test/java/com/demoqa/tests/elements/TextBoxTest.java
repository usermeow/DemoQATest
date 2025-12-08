package com.demoqa.tests.elements;

import com.demoqa.factory.UserDataFactory;
import com.demoqa.models.UserData;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("DemoQA Elements")
@Feature("Text Box Form")
public class TextBoxTest extends BaseTest {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Test(description = "Successful submission of Text Box form with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test checks that user can submit the form and view entered data in the output section")
    public void testSubmitFormWithValidData() {
        // 1. Arrange (Prepare data using Factory)
        UserData user = UserDataFactory.createDefaultUser();

        // 2. Act (Open page -> Fill -> Submit)
        textBoxPage.openPage()
                .fillForm(user)
                .clickSubmit();

        // 3. Assert (Check results)
        textBoxPage.verifyOutput(user);
    }
}