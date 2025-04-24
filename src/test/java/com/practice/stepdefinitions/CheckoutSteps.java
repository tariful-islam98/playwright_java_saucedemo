package com.practice.stepdefinitions;

import com.practice.base.BaseTest;
import com.practice.pages.CheckoutPage;
import com.practice.utilities.WebActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.testng.Assert.assertEquals;

public class CheckoutSteps {
    private final WebActions webActions;
    private final CheckoutPage checkoutPage;

    public CheckoutSteps() {
        this.webActions = new WebActions(BaseTest.getPage());
        this.checkoutPage = new CheckoutPage();
    }

    @And("I click {string}")
    public void iClick(String buttonName) {
        String selector = "text=" + buttonName;

        if (webActions.getTextContent(selector).isEmpty()) {
            assertEquals(webActions.getElement(selector).getAttribute("value"), buttonName);
        } else {
            assertEquals(webActions.getTextContent(selector), buttonName);
        }

        webActions.click(selector);
    }

    @And("I enter {string} as first name")
    public void iEnterAsFirstName(String firstName) {
        checkoutPage.enterFirstName(firstName);
    }

    @And("I enter {string} as last name")
    public void iEnterAsLastName(String lastName) {
        checkoutPage.enterLastName(lastName);
    }

    @And("I enter {string} as postal code")
    public void iEnterAsPostalCode(String postalCode) {
        checkoutPage.enterPostalCode(postalCode);
    }

    @Then("I should see a confirmation message {string}")
    public void iShouldSeeAConfirmationMessage(String message) {
        assertEquals(checkoutPage.getCompleteMessage(), message);
    }
}
