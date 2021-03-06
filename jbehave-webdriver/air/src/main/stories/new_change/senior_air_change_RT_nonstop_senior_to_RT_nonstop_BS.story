Change Itinerary Senior Passenger RT Nonstop Senior to RT Nonstop BusinessSelect

Meta:
@flow air
@process change
@user anonymous
@traveler senior
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a senior
I want to change RT Nonstop Senior to RT Nonstop BusinessSelect

Scenario: Change Itinerary -Senior Passenger- RT Nonstop Senior to RT Nonstop BusinessSelect
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|Nonstop|
    |seniorPassengerCount|1|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|
    |arrivingFlight_fareClass|BusinessSelect|
    |seniorPassengerCount|1|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
