Purchase an Anytime One Way Non Stop air ticket with CC for an Anonymous adult with disabilities

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-1040

Narrative:
As a user
I want to purchase an air ticket with CC for an anonymous adult with disabilities

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a one-way flight with disabilities options

Given I search for a one-way flight for 1 adult to add disabilities
When I book the trip
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary