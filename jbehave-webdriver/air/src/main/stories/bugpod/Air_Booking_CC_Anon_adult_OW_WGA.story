Air booking for a Want to Get Away fare

Meta:
@bugpodCoreRegression
@flow air
@process booking
@traveler adult

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a Wanna Get Away one way flight with credit card

Given I search for a one-way flight with Wanna Get Away fare
When I get to the confirmation page
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary