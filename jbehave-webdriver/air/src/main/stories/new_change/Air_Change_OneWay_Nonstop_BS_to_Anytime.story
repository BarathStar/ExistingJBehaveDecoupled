Air Change Single Anonymous Adult changes Nonstop (16)

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify as an anonymous Single Adult user on the Book Travel section of southwest.com
As a user
I want to change the Nonstop flights that match criteria for my trip so that I can successfully make a change to booked itinerary

Scenario: Change Itinerary - Air Change OneWay Nonstop BusinessSelect  to OneWay Nonstop Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |adultPassengerCount|1|
    |departureDate|+7|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departingFlight_fareClass|Anytime|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
