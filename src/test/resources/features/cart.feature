Feature: Shopping Cart

  Background:
    Given I am on the login page
    When I enter "standard_user" in the username field
    And I enter "secret_sauce" in the password field
    And I click the "Login" button
    Then I should be redirected to the inventory page

  Scenario: Add an item to the cart
    Given I am in the Inventory page
    When I click "Add to Cart" for "Sauce Labs Backpack"
    And I click the shopping cart icon
    Then the cart badge should show "1"
    And "Sauce Labs Backpack" should be in the cart

  Scenario: Remove an item from the cart
    Given I am in the Inventory page
    And I click "Add to Cart" for "Sauce Labs Backpack"
    When I click the shopping cart icon
    And I click "Remove" for "Sauce Labs Backpack" from cart items
    Then the cart should be empty
    And the cart badge should disappear