Checkin as a senior

Meta:
@flow air
@process checkin
@user anonymous
@traveler senior
@dyna_stubs

Narrative:
In order to receive my boarding pass
As a senior
I want to checkin for a flight

Scenario: senior air checkin
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|0|
    |seniorPassengerCount|1|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |isValidForCheckin|true|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|Nonstop|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I retrieve my reservation to checkin
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my senior boarding pass
