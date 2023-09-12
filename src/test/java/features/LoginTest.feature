Feature: Login

  Background:
    Given I open the page Login

  @success_login
  Scenario: Success login
    When I fill input user name "standard_user"
    Then I fill input password "secret_sauce"
    And I click on button Login
    Then The page Inventory is displayed


  @invalid_creds
  Scenario Outline: Invalid credentials
    When I fill input user name "<username>"
    Then I fill input password "<password>"
    And I click on button Login
    Then error message is displayed
    And error message with text "<errorMessageText>" is displayed
    Examples:
      | username        | password     | errorMessageText                                                          |
      | standard_user   | 452675378    | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
