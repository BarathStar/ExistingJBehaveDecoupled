Air Change Round Trip Nonstop Anytime to Round Trip Nonstop BusinessSelect

Meta:
@suite regression
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT967



Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Nonstop Anytime to Round Trip Nonstop BusinessSelect itinerary

Scenario: Changing Round Trip Nonstop Anytime to Round Trip Nonstop BusinessSelect itinerary

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
    
And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|
    |arrivingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
