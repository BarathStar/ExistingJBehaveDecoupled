Purchase a one-way anytime air ticket for 8 seniors

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@dyna_stubs
@not_passing takestoolongtorun

Narrative:
In order to verify user can book an itinerary for seniors
As an Anonymous Multi-PAX Senior users on the Book Travel section of southwest.com,
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  eight seniors, Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SAT|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 8 seniors
Then I receive an air confirmation number
