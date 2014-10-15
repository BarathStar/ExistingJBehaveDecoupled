Purchase a One Way, Any Time air ticket for an adult and senior with CC

Meta:
@bugpodCoreRegression
@flow air
@process booking
@traveler adult

Narrative:
As a user
I want to purchase an air ticket for an adult and senior with CC

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a OneWay flight for mixed pax with cc

Given I search for a one-way flight for mixed pax
When I select a flight in the adult flight page and continue to select senior flight page
And I select a flight in the senior flight page and continue to the price page
And I continue to the Purchase page
And I fill out the purchase page
Then I view my complete itinerary on the confirmation page
When I retrieve my Adult itinerary
Then I view my Adult itinerary
When I retrieve my Senior itinerary
Then I view my Senior itinerary


