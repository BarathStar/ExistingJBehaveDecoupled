Air Booking Mixed Multi-PAX Anonymous Adults and Seniors Book One-Way Direct

Meta:
@flow air
@process booking
@user anonymous
@traveler senior_adult
@dyna_stubs

Narrative:
In order to verify Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As a user
I want to find the one-way Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary -  Initial booking  One way,  Direct,  one adult and one senior, Time Zones Outside of CST, Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|MDW|
    |arrivalStation|DEN|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|

When I book a flight for 1 adults and 1 seniors with Anytime fare
Then I receive an air confirmation number