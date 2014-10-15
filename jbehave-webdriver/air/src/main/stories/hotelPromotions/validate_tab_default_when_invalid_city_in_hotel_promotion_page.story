Validate that the first tab is selected by default in the tabbed version of the Hotel Promotion page when indicating an invalid city code in its URL

Meta:

@flow hotel
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As an anonymous user in Southwest.com
I want to access the tabbed version of the Hotel Promotion page
So that I can be shown the first tab selected by default when the code indicated in the URL is not defined in any of the page's tabs

Scenario:
Validate that the first tab is selected by default in the tabbed version of the Hotel Promotion page when indicating an invalid city code in its URL

Given I am an anonymous user
When I access the tabbed version of the Hotel Promotion page having an invalid city code setup in its URL
Then I see the first tab is selected by default in the Hotel Promotion Page