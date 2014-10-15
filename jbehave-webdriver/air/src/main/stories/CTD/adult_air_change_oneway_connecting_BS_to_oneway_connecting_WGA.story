Air Change OneWay Connecting BS to OneWay Connecting WGA

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT967

Narrative: In order to verify user can change a booked itinerary
As a user
I want to Change OneWay Connecting BS to OneWay Connecting WGA itinerary

Scenario: Changing OneWay Connecting BS to OneWay Connecting WGA itinerary

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop Change Planes|

And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|WannaGetAway|
    |departureDate|+14|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
