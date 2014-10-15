Air Booking Single Anonymous Adult Books Round Trip Mixed-Service

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify Single Adult user on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Mixed-Service flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  Round Trip, Nonstop outbound, Direct - 1 stop No Plane Change inbound,  1 Anonymous Adult, Business Select
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|1 stop No Plane Change|
    |departureDate|+7|
    |returnDate|+14|
    |adultPassengerCount|1|

When I book a flight
Then I receive an air confirmation number