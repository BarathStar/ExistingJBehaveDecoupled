Multi-PAX RR Adult Books one way anytime with the middle name

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@not_passing broken

Narrative:
In order to verify user can book a flight with a middle name
As an RR Multi-PAX Adult
I want to book a flight

Scenario: Initial Itinerary - Initial booking  One way,  Nonstop,  two adults, Business Select
Given I am logged-in as an A-List Member on the Search Flights page
And I am:
  |Field|Value|
  |adultPassengerCount|2|
  |seniorPassengerCount|0|

And I have selected the following itinerary data:
  |Field|Value|
  |itineraryType|Open Jaw|
  |departureStation|DAL|
  |departureDate|+2|
  |departingFlight_carrierCode|WN|
  |departingFlight_fareClass|Anytime|
  |outboundRouting|Nonstop|
  |arrivalStation|SAT|
  |returnStation|HOU|
  |arrivingFlight_carrierCode|WN|
  |arrivingFlight_fareClass|Anytime|
  |inboundRouting|Nonstop|

When I am on the select flights page
And I continue to filling fields to purchase 2 adults
And I click on the Purchase button
Then I should see the confirmation page
