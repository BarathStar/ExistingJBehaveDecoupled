Purchase a round-trip anytime air ticket for 8 adults

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify user can book an itinerary
As a user
I want to book an itinerary for Adult Multi-Pax RT Direct Anytime

Scenario: Book an itinerary for Adult Multi-Pax RT Direct Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|1 stop No Plane Change|

When I book a flight for 8 adults
Then I receive an air confirmation number
