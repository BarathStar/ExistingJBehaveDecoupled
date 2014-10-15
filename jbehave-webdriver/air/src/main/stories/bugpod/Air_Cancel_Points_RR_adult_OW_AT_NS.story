Cancel a OW PNR with anytime fare on a non stop flight with a RR account.

Meta:
@bugpodCoreRegression
@flow air
@process cancel
@user rr_member
@traveler adult
@storyId MH-1079

GivenStories:
bugpod/Air_Booking_Points_RR_Adult_OW_AT_NS.story

Scenario: A RR member cancel the flight

Given I am on the Home page
When I access the cancel air reservation option
And I attempt to cancel my air reservation
And I confirm the air cancellation
Then I see the Cancellation Confirmation Page
