Air Change OneWay Direct WannaGetAway to OneWay Direct BusinessSelect for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change OneWay Direct WannaGetAway to OneWay Direct BusinessSelect itinerary

Scenario: Changing OneWay Direct WannaGetAway to OneWay Direct BusinessSelect itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
