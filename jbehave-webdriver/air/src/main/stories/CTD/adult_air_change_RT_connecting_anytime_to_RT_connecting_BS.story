Air Change Round Trip Connecting Anytime to Round Trip Connecting BusinessSelect

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT967

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Connecting Anytime to Round Trip Connecting BusinessSelect itinerary

Scenario: Changing Round Trip Connecting Anytime to Round Trip Connecting BusinessSelect itinerary

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|SEA|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|1 stop Change Planes|
    
And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|
    |arrivingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
