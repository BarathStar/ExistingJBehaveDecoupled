Air Booking - Mixed Multi-PAX Anonymous Adults and Seniors Book One-Way Nonstop

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT1373
@mixpax

Narrative:
In order to verify Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As a user
I want to find the one-way Nonstop flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  two adults and two seniors, Business Select

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|OAK|
    |arrivalStation|SAN|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |departureDate|+14|

When I book a flight for 2 adults and 2 seniors with Senior fare
Then I receive an air confirmation number
