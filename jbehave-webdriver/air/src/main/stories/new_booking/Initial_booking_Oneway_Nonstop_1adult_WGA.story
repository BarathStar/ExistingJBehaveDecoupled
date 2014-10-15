Purchase a one-way wanna get away air ticket for an adult

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_passing timeout

Narrative:
In order to verify user can book an itinerary
As an adult
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  one adult, WGA
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|AUS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 1 adults
Then I receive an air confirmation number
