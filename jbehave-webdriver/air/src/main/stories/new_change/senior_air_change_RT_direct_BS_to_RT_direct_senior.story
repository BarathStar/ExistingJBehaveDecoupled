Change Itinerary Senior Passenger RT direct BusinessSelect to RT direct Senior fare

Meta:
@flow air
@process change
@user anonymous
@traveler senior
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a senior
I want to change RT direct BusinessSelect to RT direct Senior fare

Scenario: Change Itinerary -Senior Passenger- RT direct BusinessSelect to RT direct Senior fare
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
    |seniorPassengerCount|1|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|Senior|
    |arrivingFlight_fareClass|Senior|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page


