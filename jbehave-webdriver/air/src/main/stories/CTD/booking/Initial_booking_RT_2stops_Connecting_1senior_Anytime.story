Air Booking - Single Anonymous Senior Books Round Trip Connecting

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler adult
@storyId SWACOMTT1412

Narrative:
In order to verify Single Senior user on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Connecting flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking Round Trip, 2 Stop Connecting, 1 Senior, Anytime

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|SFO|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|2 stops Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|2 stops Change Planes|
    |departureDate|+14|

When I book a flight as a senior
Then I receive an air confirmation number