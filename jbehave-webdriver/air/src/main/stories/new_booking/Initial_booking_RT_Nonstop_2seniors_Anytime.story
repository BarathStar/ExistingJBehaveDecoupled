Purchase a one-way anytime air ticket for 2 seniors

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to book Multi-PAX Senior users on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Nonstop flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  Round Trip,  Non Stop,  2 Seniors, Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |departureDate|+1|
    |returnDate|+2|

When I book a flight for 2 seniors
Then I receive an air confirmation number
