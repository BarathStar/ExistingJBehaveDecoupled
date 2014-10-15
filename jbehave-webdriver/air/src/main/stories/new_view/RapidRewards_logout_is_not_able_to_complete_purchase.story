Verify that a RapidReward Member cannot complete purchase after logout from Purchase page

Meta:
@dyna_stubs
@flow air
@not_live
@passenger adult
@process view
@user rr_member


Narrative:
As an Adult
In order to book a flight
I shouldn't be able to complete the purchase after logout
So that

Scenario: Verify that a RapidReward Member can not complete purchase after log out

Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a Alist Rapid Rewards member on the Search Flights page
When I select a round trip flight using points
And I continue to the Purchase page
And I log out from my Rapid Reward Account
Then I see the My Account Login page