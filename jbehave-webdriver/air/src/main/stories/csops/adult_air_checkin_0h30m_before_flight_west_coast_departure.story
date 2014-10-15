Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As an anonymous user
I want to check in 30 mins prior to my flight
So that I can receive a SD

Scenario: Online Adult Checkin less than 1h before flight retrieves security document

Given I have booked a flight from GEG to PVD departing at +0h30m
When I attempt to view the checkin details for my flight
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my security document