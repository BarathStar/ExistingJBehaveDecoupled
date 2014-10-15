Purchase a oneway business select NS air ticket for a RR adult with points with date valid for wanna get away.

Meta:
@flow air
@user rr_member
@traveler adult

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMemberWithPoints.story

Scenario: Book a One Way as a RR

Given I search for a one-way flight with Business Select fare valid for Wanna Get Away
When I book the trip with points
Then I see the confirmation for my RR points purchase
When I retrieve my itinerary
Then I view my itinerary