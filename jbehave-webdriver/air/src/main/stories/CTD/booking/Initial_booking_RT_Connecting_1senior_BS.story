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

Scenario: Initial Itinerary - Initial booking , Round Trip,  Connecting,  1 senior, Business Select

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|1 stop Change Planes|
    |departureDate|+14|

When I book a flight as a senior
Then I receive an air confirmation number