Validate that "More Rewards" page is displayed correctly when it is accessed from "Rapid Rewards" option on the top navigation bar.

Meta:
@flow rr
@process other
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a user
I want to verify that I see "More Rewards" page when I click on "More Rewards" in "Rapid Rewards" option on the top navigation bar
So that I can enhance my user experience

Scenario: Access to "More Rewards" page from "Rapid Rewards" option on the top navigation bar as anonymous in Southwest.com

Given I am on the Homepage
When I click on More Rewards
Then I am redirected to AwardHQ page