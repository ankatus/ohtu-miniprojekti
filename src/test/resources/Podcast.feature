Feature: User can save a podcast

Scenario: creating a new podcast is available in the menu
When create new lukuvinkki is selected
Then podcast is available in the menu

Scenario: podcast information can be saved
Given create new lukuvinkki is selected
And creating a new podcast is selected
And author "Longworthh, Karina" a random title and URL "http://www.youmustrememberthispodcast.com" are entered
When all items are listed
Then the random title appears on the list
