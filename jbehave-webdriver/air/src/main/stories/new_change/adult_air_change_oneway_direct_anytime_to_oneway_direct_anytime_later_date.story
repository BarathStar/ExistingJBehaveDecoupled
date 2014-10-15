Air Change OneWay Direct Anytime to OneWay Direct Anytime Later Date for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change OneWay Direct Anytime to OneWay Direct Anytime Later Date

Scenario: Change Itinerary - OneWay Direct Anytime to OneWay Direct Anytime Later Date
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|

And I have a flight booked
When I change the flight to a later date
Then I should see the itinerary change confirmation page
