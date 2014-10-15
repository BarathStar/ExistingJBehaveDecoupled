Cancel a round-trip anytime flight after checking in as an adult

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to cancel my boarding pass
As an adult
I want to checkin for a flight and then cancel it

Scenario: Adult air checkin and cancel
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|ANY|
    |arrivalStation|ANY|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |isValidForCheckin|true|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I retrieve my reservation to checkin
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
When I cancel the flight
Then I view the flight cancellation confirmation
