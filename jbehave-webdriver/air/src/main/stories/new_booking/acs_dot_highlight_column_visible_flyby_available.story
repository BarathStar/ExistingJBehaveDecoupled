Adapt ACS winner features to DOT: Highlight Column

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs
@not_passing draft

Narrative:
In order to verify the ACS highlight column new feature adapted to DOT
As an Anonymous user
I want to see the BS fare column highlighted and FlyBy message displayed

Scenario: ACS Highlight column visible and flyby available on the search flights page
Given I am flying a one-way SouthwestNS flight from an origin airport that has FlyBy available where Wanna Get Away fare is sold out
When I am on the select flights page
Then I verify that BS fares are highlighted
And I verify that departure airport has FlyBy availability