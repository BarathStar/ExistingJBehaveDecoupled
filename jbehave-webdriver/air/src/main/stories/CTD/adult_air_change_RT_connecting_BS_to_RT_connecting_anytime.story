Air Change Round Trip Connecting BusinessSelect to Round Trip Connecting Anytime

Meta:
@suite regression
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT967

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Connecting BusinessSelect to Round Trip Connecting Anytime itinerary

Scenario: Changing Round Trip Connecting BusinessSelect to Round Trip Connecting Anytime itinerary

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MDW|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|1 stop Change Planes|
    
And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|Anytime|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
