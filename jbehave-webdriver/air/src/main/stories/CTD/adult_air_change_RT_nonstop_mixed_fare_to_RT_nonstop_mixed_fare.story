Air Change Round Trip Nonstop mixed fare to Round Trip Nonstop mixed fare

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT967

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Nonstop mixed fare to Round Trip Nonstop mixed fare itinerary

Scenario: Changing Round Trip Nonstop mixed fare to Round Trip Nonstop mixed fare itinerary

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|PHX|
    |arrivalStation|SEA|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |departureDate|+10|
    |returnDate|+14|

And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
