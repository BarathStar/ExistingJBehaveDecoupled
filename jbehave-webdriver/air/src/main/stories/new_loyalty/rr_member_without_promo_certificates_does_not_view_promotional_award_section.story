Verify Promotional Awards section not displayed the

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to see My Rapid Rewards section on the account bar
As a RapidRewards Member without promotions
I want to login into my account and not be shown the promotional awards in My Rapid Rewards Section

Scenario: RR Member does not view any promotion on the Promotional Award Section
Given I am a Rapid Rewards Member passenger
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I should not see any promotional award