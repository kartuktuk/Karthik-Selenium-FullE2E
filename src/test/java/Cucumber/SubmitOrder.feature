@tag
Feature: Purchase the order from ecommerce site.

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in Confirmation Page.


    Examples:
      | name          | password        |  productName  |
      | dum@dum.dum   | Dumsdumsdums3@  |   ZARA COAT 3 |
