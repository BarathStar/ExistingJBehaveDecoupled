Air Booking - Multi-PAX Anonymous Seniors Books Round Trip Mixed-Service

Meta:
@suite regression
@project ctd
@flow air
@process booking
@traveler senior
@storyId SWACOMTT1424

Narrative:
In order to verify booking for Multi-PAX Anonymous Seniors  users on the Book Travel section of southwest.com
As an Anonymous Multi-PAX Senior users on the Book Travel section of southwest.com,
I want to find the Round Trip Mixed-Service flights that match criteria for my trip so that I can successfully make a booking.

Scenario: Initial Itinerary - Initial booking  Round Trip,  Connecting - 1 stop Plane Change outbound,  Nonstop inbound, 4 Seniors, WannaGetAway

Given air itinerary data as follows:

   |Field|Value|
   |itineraryType|Round Trip|
   |departureStation|HOU|
   |arrivalStation|BWI|
   |departingFlight_carrierCode|WN|
   |departingFlight_fareClass|WannaGetAway|
   |inboundRouting|Nonstop|
   |arrivingFlight_carrierCode|WN|
   |arrivingFlight_fareClass|WannaGetAway|
   |outboundRouting|1 stop No Plane Change|
   |departureDate|+14|
   |returnDate|+21|
   |seniorPassengerCount|4|

When I have a flight booked
Then I receive an air confirmation number
