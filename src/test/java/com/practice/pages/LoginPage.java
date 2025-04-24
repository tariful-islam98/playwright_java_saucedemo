package com.practice.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.practice.base.BaseTest;

public class LoginPage {
    private final Page page;
    private final String usernameField = "#user-name";
    private final String passwordField = "#password";
    private final String loginButton = "#login-button";
    private final String errorMessage = "//h3[@data-test='error']";

    public LoginPage() {
        this.page = BaseTest.getPage();
    }

    public void enterUsername(String username) {
        page.fill(usernameField, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordField, password);
    }

    public void clickLoginButton() {
        page.click(loginButton);
    }

    public String getErrorMessage() {
        return page.locator(errorMessage).innerText();
    }

    public Locator getLoginButton() {
        return page.locator(loginButton);
    }
}
