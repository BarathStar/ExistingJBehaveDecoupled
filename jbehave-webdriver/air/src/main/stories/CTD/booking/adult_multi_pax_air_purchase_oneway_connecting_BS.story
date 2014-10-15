Initial Purchase - Adult Multi-Pax OneWay Connecting BusinessSelect

Meta:
@project ctd
@flow air
@process booking
@traveler adult
@storyId SWACOMTT974

Narrative: In order to verify user can book an itinerary
As a user
I want to book an itinerary for Adult Multi-Pax OneWay Connecting BusinessSelect

Scenario: Book an itinerary for Adult Multi-Pax OneWay Connecting BusinessSelect

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop Change Planes|

When I book a flight for 2 adults
Then I receive an air confirmation number
