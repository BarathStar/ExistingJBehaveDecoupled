TSAPreCheck Air Checkin

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
In order to receive my security document
As an adult
I want to check in for a flight
And I see 'TSA PRE' on my security document

Scenario: Adult Checkin

Given I have a reservation for an unverified senior that is eligible for check in
When TSA PreCheck is on
And I retrieve my reservation to checkin
And I click check in to view my security document
Then I view TSA PreCheck on my security document

