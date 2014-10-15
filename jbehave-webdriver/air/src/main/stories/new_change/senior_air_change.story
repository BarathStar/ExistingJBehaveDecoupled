Change existing senior flight itinerary

Meta:
@flow air
@process change
@user anonymous
@traveler senior
@dyna_stubs

Narrative:
In order to fly on a date that I can
As a senior
I want to travel two days later than my original date and receive a updated itinerary

Scenario: senior air change  round trip
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
    |isValidForCheckin|false|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|Nonstop|

When I search and book a flight to be used for checkin, change or cancel flows from the search flights page
Given I am changing the following itinerary data to:
    |Field|Value|
    |departureDate|+2|
    |returnDate|+3|

When I change the flight and verify that the original fare was a senior fare
Then I should see the itinerary change confirmation page
