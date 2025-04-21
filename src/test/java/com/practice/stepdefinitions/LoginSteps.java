package com.practice.stepdefinitions;

import com.practice.base.BaseTest;
import com.practice.pages.InventoryPage;
import com.practice.pages.LoginPage;
import com.practice.utilities.ConfigReader;
import com.practice.utilities.WebActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps {
    private final WebActions webActions;
    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;

    public LoginSteps() {
        this.webActions = new WebActions(BaseTest.getPage());
        this.loginPage = new LoginPage();
        this.inventoryPage = new InventoryPage();
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        webActions.page.navigate(ConfigReader.getProperty("base.url"));
    }

    @When("I enter {string} in the username field")
    public void iEnterInTheUsernameField(String username) {
        loginPage.enterUsername(username);
    }

    @And("I enter {string} in the password field")
    public void iEnterInThePasswordField(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click the {string} button")
    public void iClickTheButton(String loginButton) {
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage() {
        assertTrue(webActions.page.url().contains("inventory.html"));
    }

    @And("the page title should be {string}")
    public void thePageTitleShouldBe(String title) {
        assertEquals(title, inventoryPage.getPageTitle());
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String message) {
        assertEquals(message, loginPage.getErrorMessage());
    }
}
