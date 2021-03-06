Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As an anonymous user
I want to check in 1h30 prior to I am allowed
So that I can receive an Oops message

Scenario: Online Adult Checkin between 24h and 1h prior to flight retrieves a boarding pass

Given I have booked a flight from GEG to PVD departing at +1h30m
When I attempt to view the checkin details for my flight
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view and verify my boarding pass
