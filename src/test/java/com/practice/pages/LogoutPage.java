package com.practice.pages;

import com.microsoft.playwright.Page;
import com.practice.base.BaseTest;

public class LogoutPage {
    private final Page page;
    private final String hamburgerMenu = "#react-burger-menu-btn";

    public LogoutPage() {
        this.page = BaseTest.getPage();
    }

    public void clickHamburgerMenu() {
        page.click(hamburgerMenu);
    }
}
