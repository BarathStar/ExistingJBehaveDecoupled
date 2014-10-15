Air Change OneWay Nonstop BusinessSelect to OneWay Nonstop Anytime

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a user
I want to change OneWay Nonstop BusinessSelect to OneWay Nonstop Anytime itinerary
In order to verify user can change a booked itinerary

Scenario: Changing OneWay Nonstop BusinessSelect to OneWay Nonstop Anytime itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |departureDate|+7|

When I have a flight booked
Given air itinerary data as follows:
    |Field|Value|
    |departureDate|+9|
    |departingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page