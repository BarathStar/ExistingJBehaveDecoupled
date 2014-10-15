Air Change Single Anonymous Adult changes Nonstop (16)

Meta:
@flow air
@process change
@traveler adult
@dyna_stubs

Narrative:
In order to verify as an anonymous Single Adult user on the Book Travel section of southwest.com
As a user
I want to change the Nonstop flights that match criteria for my trip so that I can successfully make a change to booked itinerary

Scenario: Change Itinerary - Air Change Round Trip Nonstop Anytime to Round Trip Nonstop Anytime to Later Date
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|SAT|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |adultPassengerCount|1|
    |departureDate|+7|
    |returnDate|+8|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departureDate|+10|
    |returnDate|+11|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page