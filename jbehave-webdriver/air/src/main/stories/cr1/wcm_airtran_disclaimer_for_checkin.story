Verify airtran disclaimer visible on checkin with CR1 toggle on

Meta:
@project cr1
@flow air
@process checkin
@user anonymous
@traveler adult
@project_in_dev
@message no dynastubs required since only verifying wcm content after direct access to page

Narrative:
As a user
I want to view the airtran disclaimer
So I can make an informed decision

Scenario: Checkin Lookup
Given I am on the check-in page
Then I view the message for airtran disclaimer