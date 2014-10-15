Air booking with multi-passenger and different fare types on IB/OB

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-1023

Narrative:
As a user
I want to purchase an air ticket for anonymous multiple adults with CC and mixed fair

GivenStories:
A_initialPage/HomePage.story

Scenario: Book a OneWay flight for adult multipax with cc

Given I search for a roundtrip flight for 3 adult(s) and mixed fair
When I book the trip
Then I view my complete itinerary on the confirmation page
When I retrieve my Adult itinerary
Then I view my Adult itinerary