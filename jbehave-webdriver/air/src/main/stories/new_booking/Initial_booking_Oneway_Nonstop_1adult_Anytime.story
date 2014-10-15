Purchase a one-way anytime air ticket for an adult

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

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  one adult, Anytime
Given air itinerary data as follows:
|Field|Value|
|itineraryType|One Way|
|departureStation|HOU|
|arrivalStation|DAL|
|departingFlight_carrierCode|WN|
|departingFlight_fareClass|Anytime|
|outboundRouting|Nonstop|
|departureDate|+14|

When I book a flight for 1 adults
Then I receive an air confirmation number
