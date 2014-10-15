Air Booking - Round Trip - Select Outbound Connecting and Inbound Direct flights

Meta:
@flow air
@process booking
@user anonymous
@traveler adult

Narrative:
In order to verify user can select Outbound Connecting and Inbound Direct flights
As an adult
I want to select Outbound Connecting and Inbound Direct flights

Scenario: Initial Itinerary - Initial booking  Round Trip, Attempt to to select Outbound Connecting and Inbound Direct flights
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|MDW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|1 stop No Plane Change|
    |departureDate|+7|
    |returnDate|+8|

When I get to the Price page
Then I should see that I am redirected to the pricing page
