Air Change OneWay Direct BS to OneWay Direct Anytime for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to Change OneWay Direct BS to OneWay Direct Anytime itinerary

Scenario: Changing OneWay Direct BS to OneWay Direct Anytime itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop No Plane Change|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|Anytime|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
