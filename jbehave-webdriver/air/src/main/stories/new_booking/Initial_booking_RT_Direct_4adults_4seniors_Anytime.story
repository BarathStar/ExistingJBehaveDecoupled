Air Booking Mixed Multi-PAX Anonymous Adults and Seniors Book Round Trip Direct

Meta:
@flow air
@process booking
@user anonymous
@traveler senior_adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify Air Booking for Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As an Anonymous Mixed Multi-PAX Adult and Senior users on the Book Travel section of southwest.com,
I want to find the Round Trip Direct flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  Round Trip,  Direct,  4 Adults and 4 Seniors, Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MCO|
    |arrivalStation|MDW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop No Plane Change|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|1 stop No Plane Change|
    |departureDate|+7|
    |returnDate|+8|

When I book a flight for 4 adults and 4 seniors
Then I receive an air confirmation number
