Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As an anonymous user
I want to check in with my 24 hours allotted time
So that I can receive a BP

Scenario: Online Adult Checkin between 24h and 1h prior to flight retrieves a boarding pass

Given I have booked a flight from PVD to GEG departing at +23h30m
When I attempt to view the checkin details for my flight
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view and verify my boarding pass
