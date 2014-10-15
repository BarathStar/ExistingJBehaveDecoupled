Change Itinerary - Round Trip Direct Anytime to Round Trip Connecting Anytime

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT976

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Direct Anytime to Round Trip Connecting Anytime

Scenario: Change Itinerary - Round Trip Direct Anytime to Round Trip Connecting Anytime

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

And I have booked a flight
Given air itinerary data as follows:

    |Field|Value|
    |outboundRouting|1 stop Change Planes|
    |inboundRouting|1 stop Change Planes|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
