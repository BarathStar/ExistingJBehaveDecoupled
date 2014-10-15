Air Booking Multi-PAX Anonymous Adult Books One-Way Nonstop

Meta:
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify user can book an itinerary
As an Anonymous Multi-PAX Adult users on the Book Travel section of southwest.com,
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  eight adults, Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SAT|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 8 adults
Then I receive an air confirmation number
