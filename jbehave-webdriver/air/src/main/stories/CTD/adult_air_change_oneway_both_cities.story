Retrieve and Change southwest oneway departure and arrival cities

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT978

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change the departure and arrival cities in a one-way itinerary

Scenario: Changing the departure and arrival cities in WN OneWay flight itinerary

Given I am flying a one-way Southwest flight
And I have booked a flight
When I change my departure city to ELP and my arrival city to HOU
Then I should see the itinerary change confirmation page
