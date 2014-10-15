Adapt ACS winner features to DOT: Upsell Modal after Select Flight Page and Highlight Column

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify the ACS feature adapted to DOT on points booking
As an Anonymous user
I want to see all the ACS feature not present on the search flights page

Scenario: ACS Highlight column, flyby and drawers not visible on the search flights page

Given I am flying a one-way SouthwestNS flight from an origin airport that has FlyBy available where Wanna Get Away fare is sold out
When I select an AT flight eligible for upselling on the select flights page with points
Then I verify that BS fares are not highlighted
Then I verify ACS drawer is not being displayed
When I attempt to get to the price page
Then I verify that the upsell modal is not being displayed
