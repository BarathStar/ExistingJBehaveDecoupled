Verify Seat Selection Button Displayed on the Rapid Rewards Itinerary Changed Page

Meta:
@project cr1
@airTranOnly
@flow upcoming trips
@process changing
@traveler adult
@user RR member
@storyId ZR-1153
@seatSelection
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button on the Rapid Rewards Itinerary Changed Page
As a Rapid Rewards Member
I want to view then modify an Upcoming Trip
So that I can see the AirTran Seat Selection Button on the Rapid Rewards Itinerary Changed Page

Scenario: Verify AirTran Seat Selection Button on Rapid Rewards Itinerary Changed Page

Given I am a Rapid Rewards Member with an existing Upcoming AirTran Trip
When I log in as a Rapid Rewards Member
And I click on my Upcoming AirTran Trip
And I modify my Upcoming Trip
Then I see the Rapid Rewards Itinerary Changed Page
And I see the seat selection button on the Rapid Rewards Itinerary Changed Page

