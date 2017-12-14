Feature: list view shows relevant data

  Scenario: user sees correct data from each lukuvinkki
    Given a lukuvinkki is added
    And list view is selected
    Then correct data can be seen
