Initial purchase - Senior Passenger - OneWay Nonstop BusinessSelect

Meta:
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT976

Narrative: Book an itinerary for a senior

Scenario: Initial purchase - Senior Passenger - OneWay Nonstop BusinessSelect

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |seniorPassengerCount|1|

When I have a flight booked
Then I receive an air confirmation number

