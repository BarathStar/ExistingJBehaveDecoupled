Air Booking Multi-PAX Anonymous Seniors Books Round Trip Direct (1)

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify Multi-PAX Senior users on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  Round Trip,  Direct with 1 stop,  2 Seniors, Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|SEA|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|1 stop No Plane Change|
    |departureDate|+7|
    |returnDate|+14|
    |seniorPassengerCount|2|

When I book a flight
Then I receive an air confirmation number