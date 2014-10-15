Anonymous One Way air booking for One Senior for a Non Stop Flight with Anytime Fare

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler senior
@storyId MH-984

Narrative:
As a user
I want to purchase an air one way ticket for one anonymous senior for a non stop flight with CC and Anytime fare

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a revenue one-way flight

Given I search for a one-way flight for 1 senior(s) with Anytime fare
When I book a anytime fare flight and continue to the price page
Then I view my complete itinerary on the confirmation page
When I retrieve my Senior itinerary
Then I view my Senior itinerary