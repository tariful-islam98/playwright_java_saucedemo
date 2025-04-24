Feature: Logout

  Background:
    Given I am on the login page
    When I enter "standard_user" in the username field
    And I enter "secret_sauce" in the password field
    And I click the "Login" button
    Then I should be redirected to the inventory page

  Scenario: Logout from the application
    Given I am in the Inventory page
    When I click the hamburger menu
    And I click "Logout"
    Then I should be redirected to the login page