Air Booking Multi-PAX Anonymous Adul@removedFromAirbookingt Books Round Trip Direct (1)

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify Air Booking for Multi-PAX Adult users on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  Round Trip,  Direct 1 stop No Plane Change,  8 Adults, Anytime
Given air itinerary data as follows:
     |Field|Value|
     |itineraryType|Round Trip|
     |departureStation|MCO|
     |arrivalStation|HOU|
     |departingFlight_carrierCode|WN|
     |departingFlight_fareClass|Anytime|
     |outboundRouting|1 stop No Plane Change|
     |arrivingFlight_carrierCode|WN|
     |arrivingFlight_fareClass|Anytime|
     |inboundRouting|1 stop No Plane Change|
     |departureDate|+7|
     |returnDate|+14|
     |adultPassengerCount|8|

When I book a flight
Then I receive an air confirmation number
