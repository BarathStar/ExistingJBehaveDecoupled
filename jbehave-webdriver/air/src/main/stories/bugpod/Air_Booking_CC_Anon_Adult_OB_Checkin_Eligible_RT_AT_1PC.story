Book a revenue round trip for anonymous adult.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-1507

Narrative:
As a user
I want to purchase an air ticket for an anonymous adult.

GivenStories:
A_initialPage/HomePage.story

Scenario: Round Trip with One Plane Change where Outbound is check-in eligible

Given I search for a round-trip flight with OB available for checkin with one change planes
When I book the trip
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary