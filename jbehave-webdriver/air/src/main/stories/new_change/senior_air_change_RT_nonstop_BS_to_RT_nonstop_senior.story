Change Itinerary Senior Passenger RT Nonstop BusinessSelect to RT Nonstop Senior fare

Meta:
@flow air
@process change
@user anonymous
@traveler senior
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a senior
I want to change RT Nonstop BusinessSelect to RT Nonstop Senior fare

Scenario: Change Itinerary -Senior Passenger- RT Nonstop BusinessSelect to RT Nonstop Senior fare
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|
    |seniorPassengerCount|1|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|Senior|
    |arrivingFlight_fareClass|Senior|
    |seniorPassengerCount|1|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
