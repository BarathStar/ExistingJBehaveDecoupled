Initial Purchase - Mixed Multi-Pax RT Nonstop Adult Anytime BusinessSelect and Senior with outbound Senior fare and inbound Anytime fare

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT974
@FF

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Mixed Multi-Pax RT Nonstop Adult Anytime BusinessSelect and Senior with outbound Senior Fare and inbound Anytime fare

Scenario: Book an itinerary for Mixed Multi-Pax RT Nonstop Adult Anytime BusinessSelect and Senior with outbound Senior Fare and inbound Anytime fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

When I book a flight for 1 adults and 1 seniors with Senior fare for outbound and Anytime fare for inbound
Then I receive an air confirmation number
