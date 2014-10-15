Purchase a one-way senior air ticket for 3 seniors

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify user can book an itinerary for seniors
As an Anonymous Multi-PAX Senior users on the Book Travel section of southwest.com,
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  three seniors, Senior
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|LAX|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 3 seniors
Then I receive an air confirmation number
