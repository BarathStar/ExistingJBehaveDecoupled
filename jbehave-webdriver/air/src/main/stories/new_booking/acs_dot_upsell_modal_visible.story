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
I want to see the ACS modal displayed between the select flight page and the price page

Scenario: ACS modal displayed between the select flight page and the price page

Given I am flying a one-way SouthwestNS flight
When I select an AT flight eligible for upselling on the select flights page with dollars
And I attempt to get to the price page
Then I verify that the upsell modal is being displayed