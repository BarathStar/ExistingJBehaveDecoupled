Log in using credentials (account number and password) from the Home page.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to access member only functionality
As an Anonymous User
I want to login by entering valid account number and password credentials

Scenario: User logs in from the Home page using valid account number and password
Given I am a Rapid Rewards Member
When I log in from the home page
Then I verify that I am logged in
