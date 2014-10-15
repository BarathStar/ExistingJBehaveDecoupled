Validate an ineligible user only gets a printed boarding pass

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project mbp
@dyna_stubs
@not_live

Narrative:
As a Southwest adult user
I want to check in online with a MBP non eligible PNR
In order to see the BP Printing Page

Scenario: Checkin a Non MBP Eligible PNR - AirTran Connections Flight departing from AUS

Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
|Field|Value|
|itineraryType|One Way|
|departureStation|AUS|
|arrivalStation|ATL|
|departingFlight_carrierCode|FL|
|isValidForCheckin|true|
|departingFlight_fareClass|WannaGetAway|
|outboundRouting|Change Planes|
|outboundConnectingStation|HOU|
And I have a flight booked

When I perform a checkin online

Then I view my boarding pass