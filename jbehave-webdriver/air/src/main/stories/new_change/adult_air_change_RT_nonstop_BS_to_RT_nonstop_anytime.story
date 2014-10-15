Air Change Round Trip Nonstop BusinessSelect to Round Trip Nonstop Anytime for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Nonstop BusinessSelect to Round Trip Nonstop Anytime itinerary

Scenario: Changing Round Trip Nonstop BusinessSelect to Round Trip Nonstop Anytime itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|Anytime|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
