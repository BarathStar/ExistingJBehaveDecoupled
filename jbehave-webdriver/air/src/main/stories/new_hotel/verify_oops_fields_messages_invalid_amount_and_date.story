Verify that southwest.com user sees Oops error messages when submitting Best Rate Guarantee form with invalid amount and invalid dates.

Meta:

@flow hotel
@traveler adult
@process other
@user anonymous
@dyna_stubs

Narrative:
As a southwest.com user
I want to see error messages for invalid amount and invalid dates on Best Rate Guarantee form page
So that I can correct them.

Scenario: Any user of southwest.com submitting a Best Rate Guarantee form with invalid amount and invalid dates

Given I am a user on BRG page
And I complete amount field with invalid amount on BRG page
And I complete dates with invalid dates on BRG page
When I submit the BRG form
Then I see invalid amount and invalid dates oops error messages on BRG form
