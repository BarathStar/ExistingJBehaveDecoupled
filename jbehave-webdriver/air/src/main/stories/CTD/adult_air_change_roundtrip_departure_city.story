Change Itinerary - Retrieve and Change southwest RoundTrip departure city

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT978

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change the departure city in a RoundTrip itinerary

Scenario: Changing the departure city in WN RoundTrip flight itinerary

Given I am flying a round-trip Southwest Southwest flight
And I have booked a flight
When I change my flight to use LAX as my departure city
Then I should see the itinerary change confirmation page
