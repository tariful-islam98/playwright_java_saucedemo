package com.practice.pages;

import com.microsoft.playwright.Page;
import com.practice.base.BaseTest;

public class CheckoutPage {
    private final Page page;
    private final String firstNameField = "#first-name";
    private final String lastNameField = "#last-name";
    private final String postalCodeField = "#postal-code";
    private final String completeMessage = "h2.complete-header";

    public CheckoutPage() {
        this.page = BaseTest.getPage();
    }

    public void enterFirstName(String firstName) {
        page.fill(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        page.fill(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        page.fill(postalCodeField, postalCode);
    }

    public String getCompleteMessage() {
        return page.locator(completeMessage).textContent();
    }
}
