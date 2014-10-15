Cancel a round-trip senior flight

Meta:
@flow air
@process cancel
@user anonymous
@traveler senior
@dyna_stubs

Narrative:
In order to cancel my flight
As a senior
I want to receive a cancellation confirmation

Scenario: Senior air cancel
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|0|
    |seniorPassengerCount|1|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|ABQ|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |isValidForCheckin|false|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|Nonstop|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
And I cancel the flight
Then I view the flight cancellation confirmation
