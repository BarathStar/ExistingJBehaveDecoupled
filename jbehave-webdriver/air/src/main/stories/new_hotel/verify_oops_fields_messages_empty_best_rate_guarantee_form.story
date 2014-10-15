Verify that southwest.com user sees Oops error messages when submitting an empty Best Rate Guarantee form.

Meta:

@flow hotel
@traveler adult
@process other
@user anonymous
@dyna_stubs

Narrative:
As a southwest.com user
I want to see error messages for empty form
So that I can correct it.

Scenario: Any user of southwest.com submitting an empty Best Rate Guarantee form

Given I am a user on BRG page
When I submit the BRG form
Then I see Required fields Oops error messages on BRG form