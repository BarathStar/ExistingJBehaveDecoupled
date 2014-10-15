Verify Minimum connection time for

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project multifleet
@removedFromAirbooking
@dyna_stubs
@not_live


Narrative:
In order to verify the minimum connection time is not met
As an adult
I want to book a Round trip flight where the minimum connection time is not when no IATA code is on the service response


Scenario: User sees MCT minimum connection time Oops message

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|LAS|
    |departureDate|+1|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|BWI|
    |returnStation|FLL|
    |returnDate|+1|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|
    |equipmentCode||

When I am on the select flights page
And I select my flights such that the Outbound arrival is less than the MCT
And I click continue to the Price page
Then I see an Oops messages from select flight page indicating that The minimum connection time in Baltimore is 45 minutes.