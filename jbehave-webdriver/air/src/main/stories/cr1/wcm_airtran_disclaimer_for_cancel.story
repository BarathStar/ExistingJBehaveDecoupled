Verify airtran disclaimer visible on cancel reservation with CR1 toggle on

Meta:
@project cr1
@flow air
@process cancel
@user anonymous
@traveler adult
@project_in_dev
@message no dynastubs required since only verifying wcm content after direct access to page

Narrative:
As a user
I want to view the airtran disclaimer
So I can make an informed decision

Scenario: Cancel Reservation Lookup
Given I am on the Cancel Air Reservation Page
Then I view the message for airtran disclaimer