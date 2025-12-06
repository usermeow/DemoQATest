package com.demoqa.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SortablePage {

    // Селекторы для Grid и List
    private ElementsCollection gridItems = $$("#demo-tabpane-grid .list-group-item");
    private ElementsCollection listItems = $$("#demo-tabpane-list .list-group-item");

    // Метод для проверки порядка элементов в Grid
    public void verifyGridOrder(String[] expectedOrder) {
        gridItems.shouldHave(CollectionCondition.size(expectedOrder.length));
        for (int i = 0; i < expectedOrder.length; i++) {
            gridItems.get(i).shouldHave(CollectionCondition.text(expectedOrder[i]));
        }
    }

    // Метод для проверки порядка элементов в List
    public void verifyListOrder(String[] expectedOrder) {
        listItems.shouldHave(CollectionCondition.size(expectedOrder.length));
        for (int i = 0; i < expectedOrder.length; i++) {
            listItems.get(i).shouldHave(CollectionCondition.text(expectedOrder[i]));
        }
    }
}
