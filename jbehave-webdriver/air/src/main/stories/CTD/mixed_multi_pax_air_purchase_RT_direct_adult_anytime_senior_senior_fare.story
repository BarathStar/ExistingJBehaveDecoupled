Initial Purchase - Mixed Multi-Pax RT Direct Adult Anytime and Senior with Anytime fare

Meta:
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Mixed Multi-Pax RT Direct Adult Anytime and Senior with Anytime Fare

Scenario: Book an itinerary for Mixed Multi-Pax RT Direct Adult Anytime and Senior with Anytime Fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|1 stop No Plane Change|

When I book a flight for 4 adults and 4 seniors with Senior fare
Then I receive an air confirmation number
