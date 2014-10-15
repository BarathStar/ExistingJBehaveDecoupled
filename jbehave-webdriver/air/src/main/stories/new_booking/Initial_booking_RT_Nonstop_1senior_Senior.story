Air Booking - Single Anonymous Senior Books Round Trip Nonstop

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@dyna_stubs

Narrative:
In order to verify user can book an itinerary for one senior
As a user
I want to find the one-way Nonstop flights for one senior so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking Round Trip, Non stop, 1 Senior, Senior Fare
Given air itinerary data as follows:
   |Field|Value|
   |itineraryType|Round Trip|
   |departureStation|LAS|
   |arrivalStation|SMF|
   |departingFlight_carrierCode|WN|
   |departingFlight_fareClass|Senior|
   |outboundRouting|Nonstop|
   |arrivingFlight_carrierCode|WN|
   |arrivingFlight_fareClass|Senior|
   |inboundRouting|Nonstop|
   |departureDate|+10|
   |returnDate|+12|

When I book a flight for 1 seniors
Then I receive an air confirmation number
