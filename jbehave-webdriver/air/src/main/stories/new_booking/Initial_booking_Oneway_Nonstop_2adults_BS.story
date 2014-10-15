Purchase a one-way busines select air ticket for 2 adults

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to fly on a date that I want
As an adult traveling with one more adult
I want to book a flight and receive both confirmation numbers

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  two adults, Business Select
Given air itinerary data as follows:
  |Field|Value|
  |itineraryType|One Way|
  |departureStation|OAK|
  |arrivalStation|SAN|
  |departingFlight_carrierCode|WN|
  |departingFlight_fareClass|BusinessSelect|
  |outboundRouting|Nonstop|
  |departureDate|+14|

When I book a flight for 2 adults
Then I receive an air confirmation number
