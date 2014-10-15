Redirect the direct rewards transfer request to the login page

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a rapid rewards member
I try to access the rewards transfer page directly
Then I should be redirected to the rewards transfer login page

Scenario: Redirect the direct rewards transfer request to the login page
Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I view the My Rapid Rewards page
And I try to go directly to the Rewards Transfer Page by entering the URL
Then I view the Rewards Transfer login page

When I login with a valid A+ member id and password
And I click the snapshot link
And I try to go directly to the Rewards Transfer Page by entering the URL
Then I view the Rewards Transfer page
