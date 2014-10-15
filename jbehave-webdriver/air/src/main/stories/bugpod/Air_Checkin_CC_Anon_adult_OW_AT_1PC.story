Anonymous user One Way air checkin for One Adult for a 1 plane change Flight with Anytime Fare.

Meta:
@bugpodCoreRegression
@flow air
@process checkin
@user anonymous
@traveler adult
@storyId MH-977

Narrative:
As a user
I want to purchase a one changes plane air ticket for an anonymous adult with CC

GivenStories:
bugpod/Air_Booking_CC_Anon_adult_checkin_eligible_OW_AT_1PC.story

Scenario: Adult passenger checkin for a flight with 1 plane change

Given I am on the Home page
When I go to the Checkin online page through the Air menu
And I attempt to view the checkin details for my flight
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass