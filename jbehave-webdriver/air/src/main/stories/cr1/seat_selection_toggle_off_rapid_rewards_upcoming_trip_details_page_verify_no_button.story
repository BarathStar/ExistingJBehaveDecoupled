Verify Seat Selection Button is not Displayed on the Rapid Rewards Upcoming Trip Details Page when seat selection toggle off

Meta:
@project cr1
@airTranOnly
@flow upcoming trips
@process changing
@traveler adult
@user RR member
@storyId ZR-1159
@seatSelectionOff
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button is not displayed on the Rapid Rewards Upcoming Trip Details Page
when seat selection toggle is off
As a Rapid Rewards Member
I want to view my Upcoming Trip Details for an existing AirTran itinerary
So that I should not see the AirTran Seat Selection Button on the Rapid Rewards Upcoming Trip Details Page

Scenario: Verify AirTran Seat Selection Button is not displayed on Rapid Rewards Upcoming Trip Details Page when toggled off

Given I am a Rapid Rewards Member with an existing Upcoming AirTran Trip
When I log in as a Rapid Rewards Member
And I click on my Upcoming AirTran Trip
Then I see the Upcoming Trips Details Page
And I should not see the AirTran Seat Selection button on the Upcoming Trips Details Page
