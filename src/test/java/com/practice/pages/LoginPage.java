package com.practice.pages;

import com.microsoft.playwright.Page;
import com.practice.base.BaseTest;

public class LoginPage {
    private final Page page;
    private final String usernameField = "#user-name";
    private final String passwordField = "#password";
    private final String loginButton = "#login-button";

    public LoginPage() {
        this.page = BaseTest.getPage();
    }

    public void enterUsername(String username){
        page.fill(usernameField, username);
    }

    public void enterPassword(String password){
        page.fill(passwordField, password);
    }

    public void clickLoginButton(){
        page.click(loginButton);
    }
}
