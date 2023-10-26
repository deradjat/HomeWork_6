Feature: Login Page Swag Labs

  Scenario: Success Login
    Given Login Page Swag Labs
    When input Username
    And input Password
    And click Login button
    Then user is on Product page

  Scenario: Failed Login
    Given Login Page Swag Labs
    When input Username
    And input invalid Password
    And click Login button
    Then User get error message