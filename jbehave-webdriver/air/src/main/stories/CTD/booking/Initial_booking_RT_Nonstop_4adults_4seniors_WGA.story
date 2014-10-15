Air Booking - Air Booking - Mixed Multi-PAX Anonymous Adults and Seniors Book Round Trip Nonstop

Meta:
@dyna_stubs
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT1419
@mixpax
@not_passing StaleElementReferenceException: Element not found in the cache
@project_AccordionPage

Narrative:
In order to verify Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As a user
I want to find the Round Trip Nonstop flights that match criteria for my trip so that I can successfully make a booking

Scenario: Initial Itinerary - Initial booking  Round Trip,  Non Stop,  4 Adults and 4 Seniors, Wanna Get Away

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
    |departureDate|+14|
    |returnDate|+15|

When I book a flight for 4 adults and 4 seniors
Then I receive an air confirmation number
