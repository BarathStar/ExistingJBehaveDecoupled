Purchase adult air Round Trip one changes plane, Anonymous Adult Anytime ticket with CC

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-1039

Narrative:
As a user
I want to purchase a one changes plane air ticket for an anonymous adult with CC

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a flight with 1 plane change

Given I search for a round-trip flight with one change planes
When I book the trip
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary