Air Booking - Air Booking - Mixed Multi-PAX Anonymous Adults and Seniors Book Round Trip Nonstop

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT1419
@mixpax
@FF

Narrative:
In order to verify Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Nonstop flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  Round Trip,  Non Stop,  4 Adults , Business Select and 4 Seniors, Senior Fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|MSY|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|
    |departureDate|+7|
    |returnDate|+14|

When I book a flight for 4 adults and 4 seniors with Senior fare
Then I receive an air confirmation number
