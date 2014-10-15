Verify successful submission of security questions and email preferences on Update Account page

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@project avengers_13.10
@project_in_dev
@storyId AV-2307/AV-2339
@passenger adult

Narrative:
In order to verify successful submission of security questions and email preferences
As a Rapid Rewards Member
I want to complete and submit the fields on the Update Account page and continue to see My Account Snapshot page

Scenario: Rapid Rewards Member reaches My Account Snapshot page upon successful submission of security questions and email preferences on Update Account page

Given I am a Rapid Rewards Member without security questions
When I login as a Rapid Rewards Member
And I complete the Update Your Rapid Rewards Account page
And I continue to see my account's settings
Then I see the My Account Snapshot page
