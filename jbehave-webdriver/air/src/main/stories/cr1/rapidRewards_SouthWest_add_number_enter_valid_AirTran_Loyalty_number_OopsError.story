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
I want to enter valid Airtran loyalty number on the Add rapid rewards page
So that I verify an oops message is displayed.


Scenario: Viewing a WN Itinerary

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I book a flight
Then I should see the confirmation page
And I click on the add Rapid Rewards number link
Given I am on the Add Rapid Rewards Number Page
When I enter my PNR on the add Rapid Rewards number page
When I enter an valid AirTran Loyalty number on the add Rapid Rewards Number page
Then I should see the oops message for valid AirTran Loyalty number on the Add Rapid Rewards page
