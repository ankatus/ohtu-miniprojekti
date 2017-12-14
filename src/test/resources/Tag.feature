Feature: user can add a new tag to the database

  Scenario: user can add a new tag
    Given "tagit" is selected
    And a new tag is added
    When "tagit" is selected
    Then that tag can be seen in the list


