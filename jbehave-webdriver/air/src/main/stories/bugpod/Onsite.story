Book the a One Way No Stop flight

Meta:
@bugpod
@flow air
@process booking
@user rr_member
@traveler adult
@storyId MH-XXXX

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMemberWithPoints.story

Scenario: Book the a One Way No Stop flight with Points for a Rapid Rewards Member using WGA fare

Given I search for a one-way flight with Wanna Get Away fare
When I book the trip with points
Then I see the confirmation for my RR points purchase
When I retrieve my itinerary
Then I view my itinerary

