SWABiz air view

Meta:
@flow air
@process view
@user sb_anonymous
@traveler adult
@not_passing draft

Narrative: In order to use SWABiz air view
As an adult traveler
I want to book a flight
so that I can see the air itinerary

Scenario: adult air view
Given I am flying a round-trip Southwest Southwest flight
When I book a flight
And I retrieve my itinerary
Then I view my itinerary
