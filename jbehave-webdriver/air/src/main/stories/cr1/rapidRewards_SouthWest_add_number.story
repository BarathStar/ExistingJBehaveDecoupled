Verify I am directed to the Enter Rapid Rewards Number page with a SouthWest Only Round-Trip

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@storyId DCQA33
@dyna_stubs


Narrative:
In order to add my flight to rapid rewards
As a customer
I want to verify SouthWest\SouthWest itinerary displayed on confirmation page


Scenario: Viewing a WN_FL Itinerary

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
And I have a flight booked
Then I should see the confirmation page
And I click on the add Rapid Rewards number link
Given I am on the Add Rapid Rewards Number Page
When I enter my PNR on the add Rapid Rewards number page
Then I should be on the Enter Rapid Rewards number page


