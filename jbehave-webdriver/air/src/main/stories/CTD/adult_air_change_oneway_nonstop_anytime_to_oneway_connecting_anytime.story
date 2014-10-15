Change Itinerary - OneWay Nonstop Anytime to OneWay Connecting Anytime

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT976

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change OneWay Nonstop Anytime to OneWay Connecting Anytime

Scenario: Change Itinerary - OneWay Nonstop Anytime to OneWay Connecting Anytime

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |arrivalStation|DAL|
    |outboundRouting|1 stop Change Planes|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
