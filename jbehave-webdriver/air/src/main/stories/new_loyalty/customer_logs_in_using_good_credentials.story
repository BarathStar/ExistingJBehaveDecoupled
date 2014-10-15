Log in using credentials (account number and password), so that I see "My Account Snapshot" page.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to see "My Account Snapshot" page
As an Anonymous User
I want to login by entering valid account number and password credentials

Scenario: User logs in by using valid account number and password
Given I am a Rapid Rewards Member
When I login from Login page
Then I see my account snapshot
