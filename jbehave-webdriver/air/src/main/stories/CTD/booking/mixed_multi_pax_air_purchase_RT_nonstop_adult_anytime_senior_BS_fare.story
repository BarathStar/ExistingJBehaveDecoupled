Initial Purchase - Mixed Multi-Pax RT Nonstop Adult Anytime and Senior with BusinessSelect fare

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Mixed Multi-Pax RT Nonstop Adult Anytime and Senior with BusinessSelect Fare

Scenario: Book an itinerary for Mixed Multi-Pax RT Nonstop Adult Anytime and Senior with BusinessSelect Fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I book a flight for 7 adults and 1 seniors with BusinessSelect fare
Then I receive an air confirmation number
