Verify Oops message error on the Select Flight Page after adding a new Flight as a traveler

Meta:
@flow air
@process change
@user anonymous
@passenger adult
@dyna_stubs
@not_live


Narrative:
As an adult
In order to add a new flight to a previous search
I want to verify an oops message is shown if I forgot to fill in required fields
So that

Scenario: verify oops message on the select flight after adding a new flight and try to doing a search without required fields filled in

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
And I am an senior traveling alone
And I am on the select flights page
When I click on the Add Another Flight hyperlink
Given I am changing the following itinerary data to:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departureDate|+1|
    |returnDate|+2|
When I select 1 adult(s) and 0 senior(s) and search
Then I should see the searched flights on the select flights page
