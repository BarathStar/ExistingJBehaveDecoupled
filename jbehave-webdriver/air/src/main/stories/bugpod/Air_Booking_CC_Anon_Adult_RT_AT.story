Book a revenue round trip

Meta:
@bugpodCoreRegression
@flow air
@process booking
@traveler adult

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a Round trip

Given I search for a round trip
When I get to the confirmation page
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary