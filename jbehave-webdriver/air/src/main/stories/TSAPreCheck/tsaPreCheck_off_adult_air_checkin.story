TSAPreCheckIn Off Air Checkin

Meta:
@suite tsaPreCheck
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@project tsa_pre_check
@project_in_dev

Narrative:
In order to receive my boarding pass
As an adult
I want to check in for a flight
And Not see 'TSA PRE' on my boarding pass


Scenario: Adult Checkin

Given I search for a roundtrip air ticket from DAL to HOU
When I book a flight eligible for checkin 1 adult
And TSA PreCheck is off
And I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I do not see TSA PreCheck on my boarding pass
