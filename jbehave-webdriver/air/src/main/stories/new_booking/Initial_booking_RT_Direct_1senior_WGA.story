Air Booking Single Anonymous Senior Books Round Trip Direct (7)

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify Single Senior user on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking Round Trip,  Direct,  one senior, WGA
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|1 stop No Plane Change|
    |departureDate|+7|
    |returnDate|+14|
    |seniorPassengerCount|1|

When I book a flight
Then I receive an air confirmation number