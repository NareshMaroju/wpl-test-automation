Feature: User login functionality

  Scenario: Successful login with valid credentials
    Given the user is on the mobile login page
    When the user enters a valid username
    And the user enters a valid password
    And the user taps on the Login button


#Feature: User login functionality
#
#  Scenario: Mobile login with invalid credentials
#    Given User launches the app
#    When User enters "invalidUser" and "invalidPass" on mobile
#    Then User should see an error message
#
#  Scenario: Web login with invalid credentials
#    Given User launches the web app
#    When User enters "invalidUser" and "invalidPass" on web
#    Then User should see an error messagethe user should be redirected to the account overview page