Verify a Rapid Rewards Member can see communication preferences in make it easier to sign in next time page

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@storyId GLO-7067
@project_in_dev
@project Flintstones_14.8

Narrative:
In order to login
As a Rappid Rewards Member without user name and security questions
I want to view corresponding communication preferences and accept rules checkbox

Scenario: Rapid Rewards Member tries to login, but needs to provide user name, security questions and sees communication preferences

Given I am a Rapid Rewards Member without user name and security questions
When I login as a Rapid Rewards Member
Then I see rapid rewards communication preferences with accept rules checkbox