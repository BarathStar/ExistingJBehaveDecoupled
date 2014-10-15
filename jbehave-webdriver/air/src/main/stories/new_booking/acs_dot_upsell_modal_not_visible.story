Adapt ACS winner features to DOT: Upsell Modal after Select Flight Page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify the ACS modal new feature adapted to DOT
As an Anonymous user
I want to see the ACS modal not displayed between the select flight page and the price page

Scenario: ACS modal not displayed between the select flight page and the price page

Given I am flying a BusinessSelect one-way SouthwestNS flight
When I search and select my flight and attempt to continue to the Price page
Then I verify that the upsell modal is not being displayed