Air Change Round Trip Direct Anytime to Round Trip Direct WannaGetAway for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Direct Anytime to Round Trip Direct WannaGetAway itinerary

Scenario: Changing Round Trip Direct Anytime to Round Trip Direct WannaGetAway itinerary
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
    
And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|WannaGetAway|
    |arrivingFlight_fareClass|WannaGetAway|
    |departureDate|+14|
    |returnDate|+21|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
