Air Change RT Round-Trip Anytime Date to RT Nonstop Anytime Later Date for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Nonstop Anytime to Round Trip Nonstop Anytime Later Date

Scenario: Change Itinerary - Round Trip Nonstop Anytime to Round Trip Nonstop Anytime Later Date

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |returningFlight_fareClass|Anytime|

And I have a flight booked
When I change the flight to a later date
Then I should see the itinerary change confirmation page
