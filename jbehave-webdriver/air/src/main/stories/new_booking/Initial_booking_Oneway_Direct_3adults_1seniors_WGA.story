Air Booking Mixed Multi-PAX Anonymous Adults and Seniors Book One-Way Direct

Meta:
@flow air
@process booking
@user anonymous
@traveler senior_adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As a user
I want to find the one-way Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary -  Initial booking  One way,  Direct,  three  adults and one senior, WGA fare for both adults and seniors

Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|SJC|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|

When I book a flight for 1 adults and 1 seniors
Then I receive an air confirmation number