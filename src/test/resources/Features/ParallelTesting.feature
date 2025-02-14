@par
Feature: Search Functionality

  Scenario: Search for "Selenium WebDriver"
    Given the user is on the Google home page
    When the user enters "Selenium WebDriver" into the search bar
    Then results related to "Selenium WebDriver" are shown on the results page

  Scenario: Search for "Cucumber BDD"
    Given the user is on the Google home page
    When the user enters "French Bulldog" into the search bar
    Then results related to "French Bulldog" are shown on the results page

  Scenario: Search for "Parallel Testing"
    Given the user is on the Google home page
    When the user enters "German Shepherd" into the search bar
    Then results related to "German Shepherd" are shown on the results page
