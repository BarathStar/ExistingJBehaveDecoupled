Verify friendly Oops message in checking page when I try to checkin NonRev flight ineligible as anonymous passenger

Meta:
@flow air
@process checkin
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an Adult
I want to verify if friendly message is shown when I try to checkin NonRev ineligible flight
So that

Scenario: I try to checkin NonRev ineligible flight and I should see a friendly Oops message

Given I am flying a round-trip Southwest Southwest flight
When I book a nonrev flight ineligible
And I check in from the check in page
Then I see Oops message that says:
 |message|
 |This non-revenue passenger is not eligible for online checkin. Please contact the Southwest Airlines Pass Bureau at 1-800-622-5865 to verify eligibility.|