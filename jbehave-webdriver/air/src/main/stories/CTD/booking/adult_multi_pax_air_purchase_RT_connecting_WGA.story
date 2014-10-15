Initial Purchase - Adult Multi-Pax RT Connecting Adult WannaGetAway

Meta:
@project ctd
@flow air
@process booking
@traveler adult
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Adult Multi-Pax RT Connecting WannaGetAway

Scenario: Book an itinerary for Adult Multi-Pax RT Connecting WannaGetAway

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|1 stop Change Planes|
    |departureDate|+7|
    |returnDate|+14|

When I book a flight for 8 adults
Then I receive an air confirmation number
