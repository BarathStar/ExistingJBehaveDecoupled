Air Change One Way Connecting WannaGetAway to One Way Connecting Anytime

Meta:
@project ctd
@flow air
@process changing
@traveler adult
@storyId SWACOMTT967

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change One Way Connecting WannaGetAway to One Way Connecting Anytime itinerary

Scenario: Changing One Way Connecting WannaGetAway to One Way Connecting Anytime itinerary

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SEA|
    |arrivalStation|PHX|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop Change Planes|
    |departureDate|+7|

And I have booked a flight

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|Anytime|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
