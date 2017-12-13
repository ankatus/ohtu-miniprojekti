Feature: User can add a multi-line comment to Lukuvinkki

  Scenario: a new comment is added and viewed
    Given list view is entered
    And a Lukuvinkki is selected
    And a comment is given with first line "kommentti alkaa" and second line "kommentti jatkuu"
    Then the comment is shown on first line "kommentti alkaa" and second line "kommentti jatkuu"

