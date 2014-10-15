Air Change Round Trip Nonstop Anytime to Round Trip Nonstop Anytime for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change RoundTrip Nonstop Anytime to RoundTrip Direct Anytime

Scenario: Change Itinerary - RoundTrip Nonstop Anytime to RoundTrip Direct Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |arrivalStation|DAL|
    |outboundRouting|1 stop No Plane Change|
    |inboundRouting|1 stop No Plane Change|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
