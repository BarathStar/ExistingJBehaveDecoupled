Verify redirect to Early Bird Message

Meta:
@project cr1
@flow air
@process booking
@traveler adult
@storyId DCQA32
@dyna_stubs
@project_in_dev

Narrative:
In order to verify I cant buy Early Bird for AirTran
As a user
I want to check my Early Bird page for the AirTran Message

Scenario: Early Bird Check-in Page AirTran Message
Given I am on the early bird page
Then I see AirTran message in page


