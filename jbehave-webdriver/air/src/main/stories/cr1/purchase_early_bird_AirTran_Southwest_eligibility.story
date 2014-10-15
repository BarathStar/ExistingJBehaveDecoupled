Verify redirect to Early Bird direct purchase path

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@user anonymous
@storyId DCAIR6397 ZR-891
@project_in_dev

Narrative:
As a customer
I want to retrieve itinerary on the Early Bird check-in page
So that I can verify Early Bird check-in eligibility


Scenario: Early Bird Check-in Eligibility

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
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I have a flight booked
And I am on the early bird page
And I retrieve itinerary on the Early Bird check-in page
Then I see all eligible flights with check box fields
And I see all non eligible flights


