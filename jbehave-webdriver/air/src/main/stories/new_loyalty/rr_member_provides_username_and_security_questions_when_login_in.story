Verify a Rapid Rewards v1 Member is able login providing user name and security questions

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@storyId PODVI-2920

Narrative:
In order to login, a Rapid Rewards v1 Member, must provide user name and security questions

Scenario: Rapid Rewards v1 Member tries to login, but needs to provide user name and security questions

Given I am a Rapid Rewards Member without user name and security questions
And I am logged in as a Rapid Rewards Member
When I complete the Username and Security Questions for my Rapid Rewards account
And I accept rules and regulations
And I click continue in Congratulations page
Then I see the My Account Snapshot page