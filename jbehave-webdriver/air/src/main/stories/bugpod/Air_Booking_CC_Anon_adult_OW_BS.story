Air booking for a Business Select fare

Meta:
@bugpodCoreRegression
@flow air
@process booking
@traveler adult
@not_passing outOfScope

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a Business Select one way flight with credit card

Given I search for a one-way flight with Business Select fare
When I get to the confirmation page
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary