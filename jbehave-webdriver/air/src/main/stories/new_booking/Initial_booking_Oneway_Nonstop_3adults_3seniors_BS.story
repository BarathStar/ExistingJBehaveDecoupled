Air Booking Mixed Multi-PAX Anonymous Adults and Seniors Book One-Way Nonstop

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
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  three adults and three seniors,  Business Select for both adults and Seniors
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SFO|
    |arrivalStation|LAX|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 3 adults and 3 seniors
Then I receive an air confirmation number
