Air Booking - Single Anonymous Adult Books Round Trip Mixed-Service

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler adult
@storyId SWACOMTT1422

Narrative:
In order to verify Single Adult user on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Mixed-Service flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  Round Trip, Connecting - 1 stop Plane Change outbound,  Nonstop inbound, 1 Anonymous Adult, WannaGetAway

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
    |departureDate|+7|
    |returnDate|+14|
    |adultPassengerCount|1|

When I have a flight booked
Then I receive an air confirmation number