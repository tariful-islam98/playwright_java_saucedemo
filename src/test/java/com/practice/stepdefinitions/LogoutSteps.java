package com.practice.stepdefinitions;

import com.practice.pages.LoginPage;
import com.practice.pages.LogoutPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertTrue;

public class LogoutSteps {
    private final LogoutPage logoutPage;
    private final LoginPage loginPage;

    public LogoutSteps() {
        this.logoutPage = new LogoutPage();
        this.loginPage = new LoginPage();
    }

    @When("I click the hamburger menu")
    public void iClickTheHamburgerMenu() {
        logoutPage.clickHamburgerMenu();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        assertTrue(loginPage.getLoginButton().isVisible());
    }
}
