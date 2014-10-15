View Airtran stations on the Change Trip Page

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@traveler adult
@user anonymous
@dyna_stubs
@storyId DCAIR-4831, ZR-125, ZR-899
@not_passing draft

Narrative:
As a customer
I want to book a roundtrip southwestcodeshare flight and change the itinerary
From the confirmation page
So that I want to see the Airtran stations on the change trip page.


Scenario: AirTran stations are visible on change trip page in the From field on a One-Way

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I book a flight
And I select to change my entire itinerary from the confirmation page
And I enter ATL in the From field
Then I see that the full city name for ATL has been autocompleted

