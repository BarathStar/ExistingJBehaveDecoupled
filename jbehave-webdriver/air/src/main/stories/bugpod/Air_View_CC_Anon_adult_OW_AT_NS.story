View the generated PNR for an anonymous adult booked with oneway anytime nonstop flight itinerary

Meta:
@bugpodCoreRegression
@flow air
@process view
@user anonymous
@traveler adult
@storyId MH-1059

Narrative:
As a user
I want to view an air reservation for an anonymous adult with CC

GivenStories:
A_initialPage/HomePage.story

Scenario: view an air reservation for one-way flight

Given I search for a one-way flight for 1 adult
When I book the trip
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary