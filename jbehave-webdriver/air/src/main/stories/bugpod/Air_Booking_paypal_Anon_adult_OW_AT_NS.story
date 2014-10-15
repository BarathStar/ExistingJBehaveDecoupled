Book a PayPal revenue flight as an A-list member

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user rr_alist
@traveler adult

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a revenue flight

Given I search for a one-way flight with Anytime fare with PayPal
When I get to the confirmation page
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary