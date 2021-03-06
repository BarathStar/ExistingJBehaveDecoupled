Air Booking - Multi-PAX Anonymous Seniors Books Round Trip Mixed-Service

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT1424

Narrative:
In order to verify booking for Multi-PAX Anonymous Seniors  users on the Book Travel section of southwest.com
As an Anonymous Multi-PAX Senior users on the Book Travel section of southwest.com,
I want to find the Round Trip Mixed-Service flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  Round Trip,  Connecting - 1 stop Plane Change outbound, Connecting - 1 stop Plane Change inbound,  4 Seniors, WannaGetAway

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DEN|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|1 stop Change Planes|
    |departureDate|+7|
    |returnDate|+8|
    |seniorPassengerCount|4|

When I have a flight booked
Then I receive an air confirmation number
