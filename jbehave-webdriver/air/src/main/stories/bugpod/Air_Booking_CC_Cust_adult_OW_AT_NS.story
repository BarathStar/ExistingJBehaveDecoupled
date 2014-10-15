Customer One Way air booking for One Adult for a Non Stop Flight with Anytime Fare.

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user customer
@traveler adult
@storyId MH-1003

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/Customer.story

Scenario: Book a One Way as a Customer

Given I search for a one-way flight with Anytime fare
When I book the trip
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary
