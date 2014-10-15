Purchase a one-way busines select air ticket for 2 seniors

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to fly on a date that I want
As a senior traveling with another senior
I want to book a flight and receive both confirmation numbers

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  two seniors, Business Select
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|OAK|
    |arrivalStation|SAN|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 2 seniors
Then I receive an air confirmation number
