RR member books an OW Adult AT NS flight with saved CC details

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user rr_member
@traveler adult
@storyId MH-1031

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMemberCC.story

Scenario: Book a One Way as a RR with saved credit card

Given I search for a one-way flight with Anytime fare
When I get to the confirmation page
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary