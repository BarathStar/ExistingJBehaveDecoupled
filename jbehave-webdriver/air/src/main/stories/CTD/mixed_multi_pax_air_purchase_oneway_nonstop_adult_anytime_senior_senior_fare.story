Initial Purchase - Mixed Multi-Pax OneWay Nonstop Adult Anytime and Senior with Senior Fare

Meta:
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Mixed Multi-Pax OneWay Nonstop Adult Anytime and Senior with Senior Fare

Scenario: Book an itinerary for Mixed Multi-Pax OneWay Nonstop Adult Anytime and Senior with Senior Fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

When I book a flight for 4 adults and 4 seniors with Senior fare
Then I receive an air confirmation number
