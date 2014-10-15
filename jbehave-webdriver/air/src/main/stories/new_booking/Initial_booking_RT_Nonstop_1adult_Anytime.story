Purchase a round-trip anytime air ticket for 1 adult

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can book an itinerary
As a user
I want to find the Round Trip Nonstop flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  Round Trip,  Nonstop,  One Anonymous Adult, Anytime
Given air itinerary data as follows:
|Field|Value|
|itineraryType|Round Trip|
|departureStation|LAS|
|arrivalStation|MSY|
|departingFlight_carrierCode|WN|
|departingFlight_fareClass|Anytime|
|outboundRouting|Nonstop|
|arrivingFlight_carrierCode|WN|
|arrivingFlight_fareClass|Anytime|
|inboundRouting|Nonstop|
|departureDate|+7|
|returnDate|+14|

When I book a flight for 1 adults
Then I receive an air confirmation number