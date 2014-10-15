Air Booking Single Anonymous Adult Books One-Way Direct (3)

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify Single Adult user on the Book Travel section of southwest.com
As a user
I want to find the one-way Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  One way,  Direct,  one adult, Business Select
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|
    |adultPassengerCount|1|

When I book a flight
Then I receive an air confirmation number
