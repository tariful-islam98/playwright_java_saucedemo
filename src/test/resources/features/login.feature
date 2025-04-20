Feature: Login functionality

  Scenario: Successful login
    Given I am on the login page
    When I enter "standard_user" in the username field
    And I enter "secret_sauce" in the password field
    And I click the "Login" button
    Then I should be redirected to the inventory page
    And the page title should be "Products"

  Scenario: Unsuccessful login
    Given I am on the login page
    When I enter "standard_user" in the username field
    And I enter "wrong_password" in the password field
    And I click the "Login" button
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"