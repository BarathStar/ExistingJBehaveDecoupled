Initial Purchase - Mixed Multi-Pax OneWay Direct Adult WGA Senior with Senior fare

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Mixed Multi-Pax OneWay Direct Adult WGA Senior with Senior fare

Scenario: Book an itinerary for Mixed Multi-Pax OneWay Direct Adult WGA Senior with Senior fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|


When I book a flight for 4 adults and 4 seniors with Senior fare
Then I receive an air confirmation number
