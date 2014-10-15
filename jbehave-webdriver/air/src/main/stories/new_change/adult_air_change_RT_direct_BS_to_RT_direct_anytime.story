Air Change Round Trip Direct BusinessSelect to Round Trip Direct Anytime for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Direct BusinessSelect to Round Trip Direct Anytime itinerary

Scenario: Changing Round Trip Direct BusinessSelect to Round Trip Direct Anytime itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|1 stop No Plane Change|
    
And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|Anytime|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
