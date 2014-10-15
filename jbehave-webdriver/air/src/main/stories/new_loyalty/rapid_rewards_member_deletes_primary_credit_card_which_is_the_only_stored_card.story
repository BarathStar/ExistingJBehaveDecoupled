Rapid Rewards Verify Primary Stored Credit Card Delete When It Is the Only Card Stored

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@project_in_dev
@project avengers_13.6

Narrative:
In order to modify payment information
As a Rapid Rewards Member
I want to verify that I am able to delete my only stored credit card which is primary

Scenario: Verify that the deleting only stored credit card which is primary is successful

Given I am a Rapid Rewards Member
And I login as a Rapid Rewards Member from Login page
And I have stored 1 credit cards
When I delete my primary card
Then I am on Edit Payment Information page without any stored credit card info