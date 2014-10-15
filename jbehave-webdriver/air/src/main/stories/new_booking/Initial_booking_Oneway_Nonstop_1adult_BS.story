Purchase a one-way business select air ticket for an adult

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can book an itinerary
As an adult
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking One way, Nonstop, one adult, Business Select
Given air itinerary data as follows:
|Field|Value|
|itineraryType|One Way|
|departureStation|LAS|
|arrivalStation|LAX|
|departingFlight_carrierCode|WN|
|departingFlight_fareClass|BusinessSelect|
|outboundRouting|Nonstop|
|departureDate|+14|

When I book a flight for 1 adults
Then I receive an air confirmation number
