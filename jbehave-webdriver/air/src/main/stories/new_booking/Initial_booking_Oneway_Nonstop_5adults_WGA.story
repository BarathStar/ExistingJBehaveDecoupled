Purchase a one-way wanna get away select air ticket for 5 adults

Meta:
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to fly on a date that I want
As an adult traveling with 4 adults
I want to book a flight and receive both confirmation numbers

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  five adults, WGA
Given air itinerary data as follows:
   |Field|Value|
   |itineraryType|One Way|
   |departureStation|DAL|
   |arrivalStation|AUS|
   |departingFlight_carrierCode|WN|
   |departingFlight_fareClass|WannaGetAway|
   |outboundRouting|Nonstop|
   |departureDate|+14|

When I book a flight for 5 adults
Then I receive an air confirmation number
