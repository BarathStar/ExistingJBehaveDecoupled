Verify I am directed to the Enter Rapid Rewards Number page with a AirTran/AirTran Round-Trip

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@user anonymous
@storyId DCQA-33, ZR-894
@dyna_stubs
@not_passing draft


Narrative:
As a customer
I want to enter invalid rapid rewards number on the Add rapid rewards number page
So that I see the oops message is displayed.

Scenario: Viewing an oops message for invalid Rapid Rewards Number

Given I am flying a round-trip AirTran AirTran flight
When I book a flight
Then I should see the confirmation page
Given I am on the Add Rapid Rewards Number Page
When I enter my PNR on the add Rapid Rewards number page
Then I should be on the Enter Rapid Rewards number page
When I enter an invalid Rapid Rewards Number on the add Rapid Rewards Number page
Then I should see the oops message for invalid Rapid Rewards Number on the Add Rapid Rewards page

