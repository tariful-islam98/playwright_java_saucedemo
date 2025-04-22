package com.practice.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.practice.base.BaseTest;

public class InventoryPage {
    private final Page page;

    private final String pageTitle = "//span[@class='title']";
    private final String allItems = "div.inventory_item_description";

    public InventoryPage() {
        this.page = BaseTest.getPage();
    }

    public String getPageTitle() {
        return page.textContent(pageTitle);
    }

    private Locator getAllItems() {
        return page.locator(allItems);
    }

    public void clickAddToCart(String productName, String buttonName) {
        for (int i = 0; i < getAllItems().count(); i++) {
            Locator item = getAllItems().nth(i);
            if (item.textContent().contains(productName)) {
                Locator button = item.locator("button");
                if (button.textContent().equalsIgnoreCase(buttonName)) {
                    button.click();
                    break;
                }
            }
        }
    }
}
