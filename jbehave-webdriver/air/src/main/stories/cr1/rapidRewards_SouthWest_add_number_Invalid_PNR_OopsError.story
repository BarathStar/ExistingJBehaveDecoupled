Verify I am directed to the Enter Rapid Rewards Number page with a SouthWest Only Round-Trip

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@user anonymous
@dyna_stubs
@storyId DCQA-33, ZR-894
@not_passing draft


Narrative:
As a customer
I want to enter Invalid PNR with southwest codeshare flight on the Add Rapid rewards page
So that I view the oops message for invalid PNR.


Scenario: Viewing a WN Itinerary

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I book a flight
Then I should see the confirmation page
And I click on the add Rapid Rewards number link
Given I am on the Add Rapid Rewards Number Page
When I enter invalid PNR on the add Rapid Rewards number page
Then I view the OOPS message for the invalid PNR on the Add Rapid Rewards page
