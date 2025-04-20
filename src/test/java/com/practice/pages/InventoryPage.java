package com.practice.pages;

import com.microsoft.playwright.Page;
import com.practice.base.BaseTest;

public class InventoryPage {
    private final Page page;

    private final String pageTitle = "//span[@class='title']";

    public InventoryPage() {
        this.page = BaseTest.getPage();
    }

    public String getPageTitle() {
        return page.textContent(pageTitle);
    }
}
