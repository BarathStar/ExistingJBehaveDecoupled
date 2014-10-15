Initial Purchase - Senior Multi-Pax RT Connecting Senior fare

Meta:
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Senior Multi-Pax RT Connecting Senior fare

Scenario: Book an itinerary for Senior Multi-Pax RT Connecting Senior fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|1 stop Change Planes|

When I book a flight for 8 seniors
Then I receive an air confirmation number
