Air Booking Multi-PAX Anonymous Adult Books Round Trip Mixed-Service

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify Multi-PAX Adult users on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Mixed-Service flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  Round Trip,  Direct - 1 stop No Plane Change outbound, Connecting - 1 stop Plane Change inbound,  8 Adults, Business Select
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

When I book a flight for 8 adults
Then I receive an air confirmation number