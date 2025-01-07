Feature: User should be able to search information using search box
  Background: user open google without log in
      Given user open google without log in
  Scenario: Searching by keyword
    When user types "french bulldog" in search box
    Then user should see results related to "french bulldog" on the page


