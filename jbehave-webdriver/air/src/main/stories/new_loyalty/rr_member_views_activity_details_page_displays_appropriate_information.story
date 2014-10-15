Validate that dot COM RR Member activity details page displays values on the Rewards Activity Details table as a RR User in Southwest

Meta:
@flow rr
@process other
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a logged-in RR member
I want to see my RR activity details page displaying on the description column the corresponding value
So that I am seeing the appropriate information.

Scenario: Rapid Reward user views activity details page displays appropriate information

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I am on Rewards Activity Details page to view appropriate information
Then I should see values on the Rewards Activity Details table
