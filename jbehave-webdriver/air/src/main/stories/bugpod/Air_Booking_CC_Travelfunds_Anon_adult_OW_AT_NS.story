Purchase adult air One Way, Anonymous Adult Anytime ticket with Travelfunds and CC

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-1065

Narrative:
As a user
I want to purchase an air ticket for an anonymous adult with Travelfunds and CC

GivenStories:
bugpod/Air_Cancel_CC_adult_OW_WGA_NS.story

Scenario: Book a revenue one-way flight with Travelfunds

Given I am on the Home page
And I search for a one-way flight for 1 adult
When I book the trip with travel funds
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary