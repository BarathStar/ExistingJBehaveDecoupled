Air Change Round Trip Direct WannaGetAway to Round Trip Direct BusinessSelect for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Direct WannaGetAway to Round Trip Direct BusinessSelect itinerary

Scenario: Changing Round Trip Direct WannaGetAway to Round Trip Direct BusinessSelect itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|1 stop No Plane Change|
    |departureDate|+14|
    |returnDate|+21|
    
And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|
    |arrivingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
