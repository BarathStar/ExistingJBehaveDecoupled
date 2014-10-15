Book a PayPal revenue flight as an A-list member

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a revenue flight

Given I navigate to the book a flight page
And I search for an open-jaw flight
When I get to the confirmation page
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary