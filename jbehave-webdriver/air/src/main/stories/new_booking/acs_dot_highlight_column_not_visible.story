Adapt ACS winner features to DOT: Highlight Column

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify the ACS highlight column new feature adapted to DOT
As an Anonymous user
I want to see the BS fare column not highlighted

Scenario: ACS Highlight column not visible
Given I am flying a one-way SouthwestNS flight leaving 15 days from today from an origin airport that has FlyBy available
When I am on the select flights page
Then I verify that BS fares are not highlighted