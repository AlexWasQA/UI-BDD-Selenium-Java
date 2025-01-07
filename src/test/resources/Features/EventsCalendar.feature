Feature: Search Event Using 'Events Calendar'
  As a user
  I want to choose an event using the 'Events Calendar' option
  So that I can find and participate in events that match my preferences

  Scenario: User successfully search for events through 'Events Calendar'
    Given the user is on the events search page
    When the user clicks the 'Events Search' button at the bottom of the page
    Then the user should be redirected to the 'Events Calendar' page
    When the user selects the following event criteria:
      | Criteria   | Value                             |
      | Event Type | Conformation: All-Breed and Group |
      | State      | Pennsylvania                      |
      | Date       | Mar 2025                          |
    When the user clicks 'Find Event' button
    Then the user should see a calendar with marked event quantities for each day
    When the user clicks on a day with events
    Then the user should see a list of events matching the selected criteria for that day
