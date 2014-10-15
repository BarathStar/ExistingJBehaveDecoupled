Verified Senior and non-verified Senior AirTran only check in

Meta:
@flow air
@project cr1
@process checkin
@user rr_member
@traveler senior
@dyna_stubs
@storyId ZR-872
@project_in_dev

Narrative:
In order to receive AirTran boarding documents for all senior passengers
As a verified senior
I want to check in online

Scenario:  An unverified senior requires airport check in

Given I have an AirTran only reservation for a verified senior and an unverified senior that is eligible for check in
When I check in from the check in page
Then I see Airport Checkin Required for the unverified senior
