Validate that a specific tab, corresponding to a city/destination, is selected by default in the tabbed version of the Hotel Promotion page

Meta:
@flow hotel
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As an anonymous user in Southwest.com
I want to access the tabbed version of the Hotel Promotion page
So that I can be shown the corresponding tab for the city/area of my destination selected by default

Scenario:
Validate that a specific tab, corresponding to a city/destination, is selected by default in the tabbed version of the Hotel Promotion page

Given I am an anonymous user
When I access the tabbed version of the Hotel Promotion page having a specific city code setup in its URL
Then I see that the tab setup in the URL is selected by default in the Hotel Promotion Page