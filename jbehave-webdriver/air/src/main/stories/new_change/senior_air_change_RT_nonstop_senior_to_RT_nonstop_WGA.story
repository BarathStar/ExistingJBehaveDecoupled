Change Itinerary Senior Passenger RT Nonstop Senior to RT Nonstop WannaGetAway fare

Meta:
@flow air
@process change
@user anonymous
@traveler senior
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a senior
I want to change RT Nonstop Senior to RT Nonstop WannaGetAway fare

Scenario: Change Itinerary -Senior Passenger- RT Nonstop Senior to RT Nonstop WannaGetAway fare
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
    |departureDate|+10|
    |returnDate|+12|
    |seniorPassengerCount|1|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departingFlight_fareClass|WannaGetAway|
    |arrivingFlight_fareClass|WannaGetAway|
    |seniorPassengerCount|1|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
