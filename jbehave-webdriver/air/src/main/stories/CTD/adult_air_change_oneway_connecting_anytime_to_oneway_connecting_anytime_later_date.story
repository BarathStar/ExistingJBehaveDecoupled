Change Itinerary - OneWay Connecting Anytime to OneWay Connecting Anytime Later Date

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT976

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change OneWay Connecting Anytime to OneWay Connecting Anytime Later Date

Scenario: Change Itinerary - OneWay Connecting Anytime to OneWay Connecting Anytime Later Date

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop Change Planes|

And I have booked a flight
When I change the flight to a later date
Then I should see the itinerary change confirmation page
