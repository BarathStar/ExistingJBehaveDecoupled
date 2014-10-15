Purchase a one-way wanna get away air ticket for a senior

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify user can book an itinerary for one senior
As a user
I want to find the one-way Nonstop flights for one senior so that I can successfully make a booking

Scenario: Initial purchase - Senior Passenger - OneWay Nonstop WannaGetAway
Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 1 seniors
Then I receive an air confirmation number

