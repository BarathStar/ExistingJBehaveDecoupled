Retrieve and Change southwest oneway departure city

Meta:
@flow air
@process change
@global_nav_regression
@traveler adult
@project ctd

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change the departure city in a one-way itinerary

Scenario: Changing the departure city in WN OneWay flight itinerary

Given I am flying a one-way Southwest flight
And I have booked a flight
When I change my flight to use ELP as my departure city
Then I should see the itinerary change confirmation page