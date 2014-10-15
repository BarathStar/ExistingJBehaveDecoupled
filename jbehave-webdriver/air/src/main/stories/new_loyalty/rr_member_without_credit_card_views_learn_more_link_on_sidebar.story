Verify the "Learn More" promo link to get the Chase Credit Card.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to see the "Learn More" promo link for the Rapid Rewards Credit Card
As a Rapid Rewards Member (without any Credit Card)
I want to access My Rapid Rewards section on the account sidebar

Scenario: Rapid Rewards Member (without a Credit Card) views "Learn More" promo link on the account sidebar
Given I am a Rapid Rewards Member without a Credit Card
When I log in from the account sidebar at the Search Flights page
And I expand My Rapid Rewards section
Then I see the Rapid Rewards Credit Card section on the account sidebar