Purchase a round-trip anytime air ticket for 5 seniors


Meta:
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify user can book an itinerary for seniors
As an Anonymous Multi-PAX Senior users on the Book Travel section of southwest.com,
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  five seniors, WGA
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|AUS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 5 seniors
Then I receive an air confirmation number
