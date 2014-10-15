Add EarlyBird to flight during purchase flow with long first and last name

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to board the plane early
As an anonymous user with a very long first and last name
I want to add EarlyBird to my flight during the purchase flow

Scenario: Add EarlyBird during purchase flow
Given I am flying a round-trip SouthwestNS flight EB eligible with Nonstop segments
When I search and select flights and continue to the Purchase page
And I fill out the purchase form with Early Bird and a long first and last name
And I complete the purchase
Then I receive an air confirmation number
And I see EarlyBird Check-In Purchased on air product on Existing purchases in trip
