Verify that southwest.com user sees Oops error messages when submitting Best Rate Guarantee form with dates which are beyond 11 months.

Meta:

@flow hotel
@traveler adult
@process other
@user anonymous
@dyna_stubs
@not_passing draft

Narrative:
As a southwest.com user
I want to see error messages for dates which are beyond 11 months
So that I can correct them.

Scenario: Any user of southwest.com submitting a Best Rate Guarantee form with dates which are beyond 11 months

Given I am a user on BRG page
And I complete dates with dates which are beyond 11 months
When I submit the BRG form
Then I see dates beyond max booking date oops error messages on BRG form
