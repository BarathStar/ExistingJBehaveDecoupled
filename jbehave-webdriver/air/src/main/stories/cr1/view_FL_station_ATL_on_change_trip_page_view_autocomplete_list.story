After booking a Southwest-Southwest Trip and Changing the Flight with ATL in the From Field Shows AirTran Station in the
Auto-complete List in the To Field

Meta:
@project cr1
@airTranOnly
@flow air
@process change
@traveler adult
@user anonymous
@dyna_stubs
@storyId DCAIR4831, ZR125, ZR-899
@not_passing Flaky test

Narrative:
As a customer
I want to book a roundtrip Southwest codeshare flight and change the itinerary from the confirmation page
So that I should see the ATL in the from field.


Scenario: AirTran stations are visible in the To field When Using an AirTran Origination Station in the To Field

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I book a flight
And I select to change my entire itinerary from the confirmation page
And I select ATL in the From field
Then I should see Atlanta, GA - ATL in From field
