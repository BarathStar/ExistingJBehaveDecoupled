Login as an A-list member on Siebel year end

Meta:
@dyna_stubs
@global_nav
@not_live
@project_in_dev

Scenario: Login A-list member on Siebel year end from global header on homepage
Given I am on the Home page
And I am a Rapid Rewards Member with status pending
When I click on the Login dropdown link
And I login as a RRMember with username and password
Then I verify that tier status is "Status Pending"