Air Booking - Mixed Multi-PAX Anonymous Adults and Seniors Book Round Trip Mixed-Service

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT1425
@mixpax


Narrative:
In order to verify booking for Mixed Multiple Adult and Senior users on the Book Travel section of southwest.com
As an Anonymous Mixed Multi-PAX Adult and Senior users on the Book Travel section of southwest.com,
I want to find the Round Trip Mixed-Service flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  Round Trip,  Nonstop outbound, Connecting - 2 stop Plane Change inbound,  4 Adults and 4 Seniors, Business Select

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|BWI|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|1 stop Change Planes|
    |departureDate|+7|
    |returnDate|+8|
    |seniorPassengerCount|4|
    |adultPassengerCount|4|

When I have a flight booked
Then I receive an air confirmation number
