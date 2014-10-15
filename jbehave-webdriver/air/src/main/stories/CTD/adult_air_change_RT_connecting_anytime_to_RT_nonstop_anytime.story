Change Itinerary - RoundTrip Connecting Anytime to RoundTrip Nonstop Anytime

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT-976

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change RoundTrip Connecting Anytime itinerary to RoundTrip Nonstop Anytime itinerary

Scenario: Changing RoundTrip Connecting Anytime itinerary to RoundTrip Nonstop Anytime itinerary

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

And I have booked a flight
Given air itinerary data as follows:

    |Field|Value|
    |arrivalStation|SMF|
    |outboundRouting|Nonstop|
    |inboundRouting|Nonstop|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page