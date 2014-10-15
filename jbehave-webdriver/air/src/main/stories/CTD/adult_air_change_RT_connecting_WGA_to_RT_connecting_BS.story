Air Change Round Trip Connecting WannaGetAway to Round Trip Connecting BusinessSelect

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT967

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Connecting WannaGetAway to Round Trip Connecting BusinessSelect itinerary

Scenario: Changing Round Trip Connecting WannaGetAway to Round Trip Connecting BusinessSelect itinerary

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MDW|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|1 stop Change Planes|
    |departureDate|+7|
    |returnDate|+14|
    
And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|
    |arrivingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
