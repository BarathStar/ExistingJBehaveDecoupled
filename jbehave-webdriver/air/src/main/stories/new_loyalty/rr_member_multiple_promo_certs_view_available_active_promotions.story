View all my active promotions.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to see all my active promotions
As a RapidRewards Member
I want to login into my account, be informed that I have more than one promo certificate and be able to view all the promotions I have

Scenario: RR Member views multiple promotions
Given I am a Rapid Rewards Member with multiple active promotions in my account
When I log in from the account sidebar at the Search Flight page
And I expand My Rapid Rewards section
Then I am informed that I have available promotions
When I request to see my promotions from the account bar
Then I view my active promotions in my account