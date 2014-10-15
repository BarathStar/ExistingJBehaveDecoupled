Air Change OneWay Direct Anytime to OneWay Connecting  Anytime

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT976

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change OneWay  Direct Anytime itinerary to OneWay Connecting Anytime itinerary

Scenario: Changing OneWay  Direct Anytime itinerary to OneWay Connecting Anytime itinerary

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|


And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |outboundRouting|1 stop Change Planes|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
