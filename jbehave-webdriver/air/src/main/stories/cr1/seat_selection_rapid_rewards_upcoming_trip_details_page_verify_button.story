Verify Seat Selection Button Displayed on the Rapid Rewards Upcoming Trip Details Page

Meta:
@project cr1
@airTranOnly
@flow upcoming trips
@process changing
@traveler adult
@user RR member
@storyId ZR-1152
@seatSelection
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button on the Rapid Rewards Upcoming Trip Details Page
As a Rapid Rewards Member
I want to view my Upcoming Trip Details for an existing AirTran itinerary
So that I can see the AirTran Seat Selection Button on the Rapid Rewards Upcoming Trip Details Page

Scenario: Verify AirTran Seat Selection Button on Rapid Rewards Upcoming Trip Details Page

Given I am a Rapid Rewards Member with an existing Upcoming AirTran Trip
When I log in as a Rapid Rewards Member
And I click on my Upcoming AirTran Trip
Then I see the Upcoming Trips Details Page
And I see the AirTran Seat Selection button on the Upcoming Trips Details Page


