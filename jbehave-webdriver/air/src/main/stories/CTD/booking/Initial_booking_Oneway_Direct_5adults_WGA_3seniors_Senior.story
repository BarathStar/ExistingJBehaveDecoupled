Air Booking - Mixed Multi-PAX Anonymous Adults and Seniors Book One-Way Direct

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler adult
@storyId SWACOMTT1374
@mixpax

Narrative:
In order to verify Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As a user
I want to find the one-way Direct flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial booking  One way,  Direct,  five adults and 3 Seniors, WGA

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SFO|
    |arrivalStation|ABQ|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|
    |departureDate|+14|

When I book a flight for 5 adults and 3 seniors with Senior fare
Then I receive an air confirmation number
