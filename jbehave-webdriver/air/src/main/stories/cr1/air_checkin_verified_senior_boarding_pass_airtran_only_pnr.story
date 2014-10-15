Verified Senior AirTran only check in

Meta:
@flow air
@project cr1
@process checkin
@user rr_member
@traveler senior
@dyna_stubs
@project_in_dev
@storyId ZR-872

Narrative:
In order to receive my boarding pass
As a verified senior
I want to check in online for an AirTran only flight

Scenario: Display AirTran boarding pass for a verified senior using online check in for AirTran only PNR

Given I am a senior age verified Rapid Rewards Member
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|FLL|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|Senior|
    |isValidForCheckin|true|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|FL|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|Nonstop|

When I book a flight eligible for checkin 1 senior
And I retrieve my reservation to checkin
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my senior boarding pass
