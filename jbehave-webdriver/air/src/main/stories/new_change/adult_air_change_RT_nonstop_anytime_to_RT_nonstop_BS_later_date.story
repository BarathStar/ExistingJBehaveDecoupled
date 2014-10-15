Air Change RT Round-Trip Anytime to RT Nonstop BusinessSelect for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify user can change a booked itinerary
As a user
I want to change RT Nonstop Anytime to RT Nonstop BusinessSelect itinerary

Scenario: Changing RT RT Nonstop Anytime to RT Nonstop BusinessSelect itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |returningFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |departureDate|+7|
    |returnDate|+14|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departureDate|+8|
    |returnDate|+15|
    |departingFlight_fareClass|BusinessSelect|
    |arrivingFlight_fareClass|BusinessSelect|
    |returningFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page