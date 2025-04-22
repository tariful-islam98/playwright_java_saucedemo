package com.practice.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.practice.base.BaseTest;

public class CartPage {
    private final Page page;

    private final String cartIcon = "a.shopping_cart_link";
    private final String cartBadge = "span.shopping_cart_badge";
    private final String itemName = "div.inventory_item_name";
    private final String allCartItems = "div.cart_item_label";
    private final String cartItem = "div.cart_item";

    public CartPage() {
        this.page = BaseTest.getPage();
    }

    public void clickCartIcon() {
        page.locator(cartIcon).click();
    }

    public String getCartBadge() {
        return page.locator(cartBadge).textContent();
    }

    public String getItemName() {
        return page.locator(itemName).textContent();
    }

    public void clickRemoveButton(String productName, String buttonName) {
        for (int i = 0; i < getAllCartItems().count(); i++) {
            Locator item = getAllCartItems().nth(i);
            if (item.textContent().contains(productName)) {
                Locator button = item.locator("button");
                if (button.textContent().equalsIgnoreCase(buttonName)) {
                    button.click();
                    break;
                }
            }
        }
    }

    public int getCartItemCount() {
        return page.locator(cartItem).count();
    }

    public int getShoppingBadgeCount() {
        return page.locator(cartBadge).count();
    }

    private Locator getAllCartItems() {
        return page.locator(allCartItems);
    }
}
