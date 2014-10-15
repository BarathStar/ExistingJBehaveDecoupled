Air Change OneWay Nonstop Anytime to OneWay Nonstop Anytime Later Date for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change OneWay Nonstop Anytime to OneWay Nonstop Anytime Later Date

Scenario: Change Itinerary - OneWay Nonstop Anytime to OneWay Nonstop Anytime Later Date
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have a flight booked
When I change the flight to a later date
Then I should see the itinerary change confirmation page
