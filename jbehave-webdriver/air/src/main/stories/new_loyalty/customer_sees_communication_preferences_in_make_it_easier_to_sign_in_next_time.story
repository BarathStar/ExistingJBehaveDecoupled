Verify a Non Rapid Rewards Member (Customer) can see communication preferences in make it easier to sign in next time page

Meta:
@flow rr
@process loyalty
@user customer
@traveler adult
@dyna_stubs
@not_live
@storyId GLO-7067
@project_in_dev
@project Flintstones_14.8

Narrative:
In order to login
As a Customer user without user name and security questions
I want to view corresponding communication preferences

Scenario: Non Rapid Rewards Member (Customer) tries to login, but needs to provide user name, security questions and sees communication preferences

Given I am a Customer without user name and security questions
When I login as Customer
Then I see Customer communication preferences
