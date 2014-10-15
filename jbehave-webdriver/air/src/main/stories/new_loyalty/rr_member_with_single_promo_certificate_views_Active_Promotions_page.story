Verify expiration date and access its detailed information at Active Promotions page.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to see the only active promotions in my account
As a RapidRewards Member
I want to login into my account and view the information of my single promotion

Scenario: RR Member views a single promotion
Given I am a Rapid Rewards Member with only one active promotion in my account
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I see my promotion with its expiration date
When I request to see my promotions from the account bar
Then I view my active promotions in my account