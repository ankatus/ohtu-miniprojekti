Feature: User can save a video

Scenario: creating a new video is available in the menu
When create new lukuvinkki is selected
Then "video" is available in the menu

Scenario: video information will be saved
Given create new lukuvinkki is selected
And creating a new video is selected
And author "Seb Rose", title "Cucumber" and URL "https://youtube.com/watch?v=MCaXumfckmQ" are entered
When all items are listed
Then the created lukuvinkki with author "Seb Rose", title "Cucumber" appers on the list

