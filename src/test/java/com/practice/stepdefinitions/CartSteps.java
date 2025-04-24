package com.practice.stepdefinitions;

import com.practice.base.BaseTest;
import com.practice.pages.CartPage;
import com.practice.pages.InventoryPage;
import com.practice.utilities.WebActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartSteps {
    private final WebActions webActions;
    private final InventoryPage inventoryPage;
    private final CartPage cartPage;


    public CartSteps() {
        this.webActions = new WebActions(BaseTest.getPage());
        this.inventoryPage = new InventoryPage();
        this.cartPage = new CartPage();
    }

    @Given("I am in the Inventory page")
    public void iAmInInventoryPage() {
        assertTrue(webActions.page.url().contains("inventory.html"));
    }

    @When("I click {string} for {string}")
    public void iClickFor(String buttonName, String productName) {
        inventoryPage.clickAddToCart(productName, buttonName);
    }

    @And("I click the shopping cart icon")
    public void iClickTheShoppingCartIcon() {
        cartPage.clickCartIcon();
    }

    @Then("the cart badge should show {string}")
    public void theCartBadgeShouldShow(String badgeCount) {
        assertEquals(cartPage.getCartBadge(), badgeCount);
    }

    @And("{string} should be in the cart")
    public void shouldBeInTheCart(String itemName) {
        assertEquals(cartPage.getItemName(), itemName);
    }

    @And("I click {string} for {string} from cart items")
    public void iClickForFromCartItems(String buttonName, String productName) {
        cartPage.clickRemoveButton(productName, buttonName);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        assertEquals(cartPage.getCartItemCount(), 0);
    }

    @And("the cart badge should disappear")
    public void theCartBadgeShouldDisappear() {
        assertEquals(cartPage.getShoppingBadgeCount(), 0);
    }
}
