Air Change OneWay Connecting Anytime to OneWay Connecting BusinessSelect

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT967

Narrative: In order to verify user can change a booked itinerary
As a user
I want to Change OneWay Connecting Anytime to OneWay Connecting BusinessSelect itinerary

Scenario: Changing OneWay Connecting Anytime to OneWay Connecting BusinessSelect itinerary

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop Change Planes|

And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
