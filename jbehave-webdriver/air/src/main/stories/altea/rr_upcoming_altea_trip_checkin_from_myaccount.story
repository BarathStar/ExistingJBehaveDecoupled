Verify Check In Online button from My Account
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
I want to click "Check In" button under "Upcoming Trips" on the account page

Scenario: Redirect to Altea check-in page for upcoming Altea flight

Given I am a Rapid Rewards Member with an Altea upcoming trip today
When I click Check In button under Upcoming Trips section on My Account page
Then I am redirected to the Altea Check In page
