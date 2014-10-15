View Airtran stations on the Change Trip Page When The New Origin (From Field) is an AirTran Station

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@traveler adult
@user anonymous
@dyna_stubs
@storyId DCAIR-4831, ZR-125, ZR-899

Narrative:
As a customer
I want to book a Roundtrip Codeshare flight and change the flight from the confirmation page
So that when I am on the change trip page I see the autocomplete list for for From, To and Return fields.

Scenario: AirTran stations are visible on change trip page in the From, To and Return field

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I book a flight
And I select to change my entire itinerary from the confirmation page
And I select CAK in the From field
Then I should see Canton, OH - CAK in From field
When From field is empty
And I select ATL in the To field
Then I should see Atlanta, GA - ATL in To field
When From, To field is empty
And I select ATL in the Return field dropdown
Then I should see Atlanta, GA - ATL in Return field
