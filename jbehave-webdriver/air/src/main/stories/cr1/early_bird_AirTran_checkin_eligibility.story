Verify AirTran eligibility for Early Bird direct purchase.

Meta:
@project cr1
@airTranOnly
@flow air
@storyId DCAIR6397
@dyna_stubs

Narrative: In order to verify AirTran eligibility for Early Bird
As a user
I want to view AirTran flights in the Early Bird direct purchase flow


Scenario: Early Bird Check-in AirTran only

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|BOS|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|FL|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I have a flight booked
And I am on the early bird page
And I retrieve itinerary on the Early Bird check-in page
Then I see all non eligible flights
And I see the non eligible AirTran message



