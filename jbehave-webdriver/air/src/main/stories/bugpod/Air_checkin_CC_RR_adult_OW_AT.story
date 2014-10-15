Checkin a Rapid Reward member for a OW nonstop flight with anytime fare

Meta:
@bugpodCoreRegression
@flow air
@process checkin
@user anonymous
@traveler adult
@storyId MH-1002

Narrative:
As a user
I want to purchase a one changes plane air ticket for an RR adult with CC

GivenStories:
bugpod/Air_Booking_CC_RR_adult_checkin_eligible_OW_AT_NS.story

Scenario: Adult RR passenger checkin for a flight

Given I am on the Home page
When I go to the Checkin online page through the Air menu
And I attempt to view the checkin details for my flight
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass