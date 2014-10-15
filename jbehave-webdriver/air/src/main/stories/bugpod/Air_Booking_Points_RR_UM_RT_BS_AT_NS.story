Purchase a round-trip BS outbound and AT inbound air ticket for a RRmember unaccompanied minor with points

Meta:
@bugpod
@flow air
@process booking
@user rr_member
@traveler unaccompanied minor
@storyId MH-1394

Narrative:
As a user
I want to purchase an air ticket for a RRmember unaccompanied minor with points

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMinorWithPoints.story

Scenario: Book a revenue round-trip for an UM

Given I search for a round-trip flight for a minor
When I select Business Select for the outbound flight
And I select Anytime for the inbound flight
And I select flights with points and continue
And I continue and fill the purchase page as an UM with points
Then I see the confirmation for my RR points purchase