Verify that user selecting gift card link in the page header will go to the correct URL.

Meta:
@flow air
@traveler adult
@process view
@user anonymous
@dyna_stubs

Narrative:
As a southwest.com user
I want to be able to access the "southwestcard" page.
So that I can read information on SWA gift cards.

Scenario: Any visitor to southwest.com selects "Southwest Gift Cards" link on the homepage.

Given I am on the Homepage
When I select southwestgiftcard in the page header
Then I see the Southwest Gift Cards home page