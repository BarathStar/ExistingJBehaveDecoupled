Verify that southwest.com user sees Oops error messages when submitting Best Rate Guarantee form with invalid email and past dates.

Meta:

@flow hotel
@traveler adult
@process other
@user anonymous
@dyna_stubs



Narrative:
As a southwest.com user
I want to see error messages for invalid email and past dates
So that I can correct them.

Scenario: Any user of southwest.com submitting a Best Rate Guarantee form with invalid email and past dates

Given I am a user on BRG page
And I complete email field with invalid email on BRG page
And I complete dates with past dates on BRG page
When I submit the BRG form
Then I see invalid email and past dates oops error messages on BRG form
