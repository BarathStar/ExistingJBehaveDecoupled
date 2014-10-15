Purchase adult air One Way, Anonymous Adult Anytime ticket with CC and add RR# after purchase

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-1025

Narrative:
As a user
I want to purchase an air ticket for an anonymous adult with CC

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMember.story,
A_loginStories/Logout.story

Scenario: Book a revenue one-way flight

Given I search for a one-way flight for 1 adult
When I book the trip
Then I view my complete itinerary on the confirmation page
When I click on Add Rapid Rewards Number link
And I enter my PNR on the add Rapid Rewards number page
And I enter a Rapid Rewards Number
Then I see my Rapid Rewards Number added to my itinerary
When I retrieve my itinerary
Then I view my itinerary