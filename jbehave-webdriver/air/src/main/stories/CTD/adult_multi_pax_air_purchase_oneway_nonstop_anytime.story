Initial Purchase - Adult Multi-Pax OneWay Nonstop Anytime

Meta:
@project ctd
@flow air
@process booking
@traveler adult
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Adult Multi-Pax OneWay Nonstop Anytime

Scenario: Book an itinerary for Adult Multi-Pax OneWay Nonstop Anytime

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

When I book a flight for 8 adults
Then I receive an air confirmation number
