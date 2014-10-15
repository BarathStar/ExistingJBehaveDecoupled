Log in using credentials (account number and password) from the Home page and then logout.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to log out from my account
As a logged in loyalty member
I want to logout from the Home page by clicking the logout link

Scenario: User logs in from the Home page using valid account number and password and then logs out
Given I am a Rapid Rewards Member
When I log in from the home page
And I click on the logout link
Then I verify that I am logged out
