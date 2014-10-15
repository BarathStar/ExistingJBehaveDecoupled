Air Booking Multi-PAX Anonymous Seniors Book One-Way Direct

Meta:
@flow air
@process booking
@user anonymous
@traveler senior
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify Multi-PAX Senior users on the Book Travel section of southwest.com
As a user
I want to find the one-way Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking One way, Direct, three seniors, Senior
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|1 stop No Plane Change |
    |departureDate|+14|

When I book a flight for 3 seniors
Then I receive an air confirmation number