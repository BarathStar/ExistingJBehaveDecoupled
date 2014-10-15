Air Booking Multi-PAX Anonymous Adult Books One-Way Direct

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify Multi-PAX Adult users on the Book Travel section of southwest.com
As a user
I want to find the one-way Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary -  Initial booking  One way,  Direct,  five adults, WGA
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SFO|
    |arrivalStation|ABQ|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|

When I book a flight for 5 adults
Then I receive an air confirmation number