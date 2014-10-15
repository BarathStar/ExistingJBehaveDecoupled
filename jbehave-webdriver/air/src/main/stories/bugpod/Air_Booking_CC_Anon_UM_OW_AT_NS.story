Purchase a oneway anytime air ticket for an anonymous unaccompanied minor with credit card

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler unaccompanied minor
@storyId MH-1048

Narrative:
As a user
I want to purchase an air ticket for an anonymous unaccompanied minor with CC

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a revenue one-way flight

Given I search for a one-way flight for a minor
When I book a flight for UM
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary