Change Itinerary - Retrieve and Change southwest RoundTrip both cities

Meta:
@suite regression
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT978

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change both cities in a RoundTrip itinerary

Scenario: Changing both cities in WN RoundTrip flight itinerary

Given I am flying a round-trip Southwest Southwest flight
And I have booked a flight
When I change my departure city to LAX and my arrival city to DEN
Then I should see the itinerary change confirmation page
