Multiple Adults One way booking for a Non Stop Flight with Anytime Fare with Credit card as form of payment

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-995

Narrative:
As a user
I want to purchase an air ticket for anonymous multiple adults with CC

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a OneWay flight for adult multipax with cc

Given I search for a one-way flight for 3 adults
When I book the trip
Then I view my complete itinerary on the confirmation page
When I retrieve my Adult itinerary
Then I view my Adult itinerary