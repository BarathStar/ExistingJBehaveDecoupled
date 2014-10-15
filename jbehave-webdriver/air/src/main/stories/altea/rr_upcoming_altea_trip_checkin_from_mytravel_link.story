Verify Check In Online button under My Travel on the account side bar
Meta:
@flow air
@process checkin
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@not_live
@project_in_dev
@storyid DCAIR-7701

Narrative:
In order to see altea check in page
As a Rapid Rewards Member
I want to click "Check In Online" button under "My Travel" on the account side bar

Scenario: Redirect to Altea check-in page for upcoming Altea flight

Given I am a Rapid Rewards Member with an Altea upcoming trip today
When I click Check In Online under My Travel in Global Nav bar
Then I am redirected to the Altea Check In page
