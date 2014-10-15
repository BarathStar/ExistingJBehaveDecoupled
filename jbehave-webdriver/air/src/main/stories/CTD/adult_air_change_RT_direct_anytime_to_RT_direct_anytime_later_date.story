Change Itinerary - Round Trip Direct Anytime to Round Trip Direct Anytime Later Date

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT976

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Direct Anytime to Round Trip Direct Anytime Later Date

Scenario: Change Itinerary - Round Trip Direct Anytime to Round Trip Direct Anytime Later Date

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|1 stop No Plane Change|


And I have booked a flight
When I change the flight to a later date
Then I should see the itinerary change confirmation page
