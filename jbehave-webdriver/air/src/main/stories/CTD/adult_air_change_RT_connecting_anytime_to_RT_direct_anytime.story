Change Itinerary - Round Trip Connecting Anytime to Round Trip Direct Anytime

Meta:
@suite regression
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT976


Narrative: In order to verify user can change a booked itinerary
As a user
I want to change Round Trip Connecting Anytime to Round Trip Direct Anytime

Scenario: Change Itinerary - Round Trip Connecting Anytime to Round Trip Direct Anytime

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|1 stop Change Planes|
    |departureDate|+2|
    |returnDate|+8|

And I have booked a flight
Given air itinerary data as follows:

    |Field|Value|
    |outboundRouting|1 stop No Plane Change|
    |inboundRouting|1 stop No Plane Change|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
