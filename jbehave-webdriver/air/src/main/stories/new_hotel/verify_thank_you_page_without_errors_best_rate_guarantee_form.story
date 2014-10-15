Verify that BRG Thank you page is accessed when Southwest.com user submits Best Rate Guarantee page is submitted without errors.

Meta:

@flow hotel
@traveler adult
@process other
@user anonymous
@dyna_stubs
@not_live

Narrative:
As a southwest.com user
I want to access BRG Thank you page
So that I can see that my claim was submitted.

Scenario: Any user of southwest.com accessing BRG Thank you page

Given I am a user on BRG page
And I complete properly all fields on BRG page
When I submit the BRG form
Then I access BRG Thank you page
