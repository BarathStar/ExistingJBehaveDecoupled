Initial Purchase - Senior Multi-Pax OneWay Nonstop with Senior fare

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Senior Multi-Pax OneWay Nonstop with Senior fare

Scenario: Book an itinerary for Senior Multi-Pax OneWay Nonstop with Senior fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|Nonstop|

When I book a flight for 8 seniors
Then I receive an air confirmation number
