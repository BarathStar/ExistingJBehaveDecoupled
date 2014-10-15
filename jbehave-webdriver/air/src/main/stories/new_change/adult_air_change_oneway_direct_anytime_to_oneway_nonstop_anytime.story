Air Change Itinerary OneWay Direct Anytime to OneWay Nonstop Anytime for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change OneWay Direct Anytime itinerary to OneWay Nonstop Anytime itinerary

Scenario: Changing OneWay Direct Anytime itinerary to OneWay Nonstop Anytime itinerary
Given air itinerary data as follows:
   |Field|Value|
   |itineraryType|One Way|
   |departureStation|LAS|
   |arrivalStation|DAL|
   |departingFlight_carrierCode|WN|
   |departingFlight_fareClass|Anytime|
   |outboundRouting|1 stop No Plane Change|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |arrivalStation|SMF|
    |outboundRouting|Nonstop|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
