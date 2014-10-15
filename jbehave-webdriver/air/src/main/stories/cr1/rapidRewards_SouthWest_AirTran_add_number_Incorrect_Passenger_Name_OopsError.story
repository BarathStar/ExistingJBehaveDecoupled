Verify I am directed to the Enter Rapid Rewards Number page with a SouthWest/AirTran Round-Trip
!-- To be undrafted after sauce labs performance issues with bubble up menu is addressed
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
I want to enter incorrect passenger name using Southwestcodeshare PNR on the Add Rapid rewards page
So that I verify oops message is displayed.


Scenario: Viewing a WN_FL Itinerary

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight
Then I should see the confirmation page
Then I click on the add Rapid Rewards number link
Given I am on the Add Rapid Rewards Number Page
When I enter my PNR and invalid Passenger Name on the add Rapid Rewards number page
Then I should see the oops message for invalid Passenger Name
