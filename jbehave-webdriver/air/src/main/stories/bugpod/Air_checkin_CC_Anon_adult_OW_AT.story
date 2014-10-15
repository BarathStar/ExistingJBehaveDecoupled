Checkin an anonymous adult for a OW nonstop flight with anytime fare

Meta:
@bugpodCoreRegression
@flow air
@process checkin
@user anonymous
@traveler adult
@storyId MH-1001

Narrative:
As a user
I want to purchase an one way air ticket for an anonymous adult with CC

GivenStories:
bugpod/Air_Booking_CC_Anon_adult_checkin_eligible_OW_AT_NS.story

Scenario: Adult passenger checkin for a one way any time flight

Given I am on the Home page
When I go to the Checkin online page through the Air menu
And I attempt to view the checkin details for my flight
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass