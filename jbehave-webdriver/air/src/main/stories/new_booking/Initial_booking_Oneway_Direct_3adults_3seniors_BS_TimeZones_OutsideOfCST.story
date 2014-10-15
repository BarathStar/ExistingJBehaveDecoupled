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

Scenario: Initial booking  One way,  Direct,  three adults and three seniors, Time Zones Outside of CST, Business Select
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|

When I book a flight for 3 adults and 3 seniors with BusinessSelect fare
Then I receive an air confirmation number