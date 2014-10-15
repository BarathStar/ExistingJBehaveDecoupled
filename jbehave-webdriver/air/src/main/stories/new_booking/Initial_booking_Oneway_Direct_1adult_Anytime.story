Purchase a one-way anytime air ticket for an adult

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify Single Adult user on the Book Travel section of southwest.com
As an adult
I want to find the one-way Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  One way,  Direct,  one adult, Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|MDW|
    |arrivalStation|MSY|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|
    |adultPassengerCount|1|

When I book a flight
Then I receive an air confirmation number