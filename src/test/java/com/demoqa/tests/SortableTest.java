package com.demoqa.tests;

import com.demoqa.pages.SortablePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class SortableTest {

    private SortablePage sortablePage;

    @BeforeMethod
    public void setup() {
        sortablePage = new SortablePage();
        open("https://demoqa.com/sortable"); // URL твоей страницы
    }

    @Test
    public void testSortableGrid() {
        String[] expectedGridOrder = {"One", "Two", "Three", "Four", "Five", "Six"};
        sortablePage.verifyGridOrder(expectedGridOrder);
    }

    @Test
    public void testSortableList() {
        String[] expectedListOrder = {"One", "Two", "Three", "Four", "Five"};
        sortablePage.verifyListOrder(expectedListOrder);
    }
}
