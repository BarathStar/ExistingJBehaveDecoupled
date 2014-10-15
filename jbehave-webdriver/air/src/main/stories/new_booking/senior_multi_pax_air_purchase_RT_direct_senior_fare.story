Purchase a one-way senior air ticket for 8 seniors

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Senior Multi-Pax RoundTrip Direct with Senior fare

Scenario: Book an itinerary for Senior Multi-Pax RoundTrip Direct with Senior fare
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|1 stop No Plane Change|

When I book a flight for 8 seniors
Then I receive an air confirmation number