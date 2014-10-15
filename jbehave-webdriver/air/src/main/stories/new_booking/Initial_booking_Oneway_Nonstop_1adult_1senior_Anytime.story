Mixed multi-pax one way nonstop anytime booking for adults and seniors

Meta:
@flow air
@process booking
@user anonymous
@traveler senior_adult
@dyna_stubs

Narrative:
In order to verify Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As a user
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  one  adult and one senior, Anytime fare for both adults and seniors
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|MCI|
    |arrivalStation|STL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 1 adults and 1 seniors
Then I receive an air confirmation number
