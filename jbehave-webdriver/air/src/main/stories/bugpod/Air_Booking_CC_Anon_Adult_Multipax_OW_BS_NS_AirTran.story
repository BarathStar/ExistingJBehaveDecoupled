Air booking AirTran only for multi-passenger

Meta:
@bugpod
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-1537

Narrative:
As a user
I want to purchase an air ticket for anonymous multiple adults with CC

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a OneWay flight AirTran Only for adult multipax with cc

Given I search for an Airtran one-way flight for 2 adults with Business Select fare
When I get to the confirmation page
Then I view my complete itinerary on the confirmation page
