Rapid Rewards Verify Primary Stored Credit Card Delete

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@project_in_dev
@project avengers_13.6

Narrative:
In order modify the payment information
As a Rapid Rewards Member
I want to verify that when I delete my primary credit card then another card is set as the primary

Scenario: Verify that the deleting primary card sets another card as primary

Given I am a Rapid Rewards Member
And I login as a Rapid Rewards Member from Login page
And I have stored 3 credit cards
When I delete my primary card
Then I see 2 credit cards with another card marked primary