Validate that "Buy Points" page is displayed correctly when it is accessed from "Rapid Rewards" option on the top navigation bar.

Meta:
@flow rr
@process other
@user anonymous
@traveler adult
@dyna_stubs
@points_dot_com_off

Narrative:
As a user
I want to verify that I see "Buy Points" page when I click on "Buy Points" in "Rapid Rewards" option on the top navigation bar
So that I can enhance my user experience

Scenario: Access to "Buy Points" page from "Rapid Rewards" option on the top navigation bar as anonymous in Southwest.com

Given I am on the Homepage
When I click on Buy Points
Then I see Buy Points page