Feature: Checkout

  Background:
    Given I am on the login page
    When I enter "standard_user" in the username field
    And I enter "secret_sauce" in the password field
    And I click the "Login" button
    Then I should be redirected to the inventory page

  Scenario: Complete checkout with an item
    Given I am in the Inventory page
    When I click "Add to Cart" for "Sauce Labs Backpack"
    And I click the shopping cart icon
    And I click "Checkout"
    And I enter "John" as first name
    And I enter "Doe" as last name
    And I enter "12345" as postal code
    And I click "Continue"
    And I click "Finish"
    Then I should see a confirmation message "Thank you for your order!"