Validate that "Rapid Rewards Shopping" page is displayed correctly when it is accessed from "Rapid Rewards" option on the top navigation bar.

Meta:
@flow rr
@process other
@user anonymous
@traveler adult
@dyna_stubs
@not_passing new_global_nav

Narrative:
As a user
I want to verify that I see "Rapid Rewards Shopping" page when I click on "Rapid Rewards Shopping" in "Rapid Rewards" option on the top navigation bar
So that I can access that page easily

Scenario: Access to "Rapid Rewards Shopping" page from "Rapid Rewards" option on the top navigation bar as anonymous in Southwest.com

Given I am on the Homepage
When I click on Rapid Rewards Shopping
Then I see Rapid Rewards Shopping page