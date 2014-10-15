Air Change Round Trip Direct Anytime to Round Trip Direct Anytime for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change RoundTrip Direct Anytime itinerary to RoundTrip Nonstop Anytime itinerary

Scenario: Changing RoundTrip Direct Anytime itinerary to RoundTrip Nonstop Anytime itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|
    |inboundRouting|1 stop No Plane Change|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |arrivalStation|SMF|
    |outboundRouting|Nonstop|
    |inboundRouting|Nonstop|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page