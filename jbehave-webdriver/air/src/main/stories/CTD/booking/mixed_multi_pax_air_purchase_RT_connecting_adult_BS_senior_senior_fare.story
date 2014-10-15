Initial Purchase - Mixed Multi-Pax RT Connecting Adult BusinessSelect and Senior with Senior fare

Meta:
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Mixed Multi-Pax RT Connecting Adult BusinessSelect and Senior with Senior Fare

Scenario: Book an itinerary for Mixed Multi-Pax RT Connecting Adult BusinessSelect and Senior with Senior Fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|1 stop Change Planes|

When I book a flight for 4 adults and 4 seniors with Senior fare
Then I receive an air confirmation number
