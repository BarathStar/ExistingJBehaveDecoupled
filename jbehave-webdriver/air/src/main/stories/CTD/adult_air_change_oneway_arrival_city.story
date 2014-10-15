Retrieve and Change southwest oneway arrival city

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT978

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change the arrival city in a one-way itinerary

Scenario: Changing the arrival city in WN OneWay flight itinerary

Given I am flying a one-way Southwest flight
And I have booked a flight
When I change my flight to use HOU as my arrival city
Then I should see the itinerary change confirmation page
