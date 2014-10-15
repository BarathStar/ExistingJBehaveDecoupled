Book a Oneway adult PNR with anytime fare using Gift Card as form of payment

Meta:
@bugpodCoreRegression
@flow air
@process booking
@traveler adult
@storyId MH-1010

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a revenue flight with Gift card

Given I search for a one-way flight with Anytime fare with southwestgiftcard
When I get to the confirmation page
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary