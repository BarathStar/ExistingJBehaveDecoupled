Senior RR member books an RT Senior AT NS flight with saved CC details

Meta:
@bugpod
@flow air
@process booking
@user rr_member
@traveler senior
@storyId MH-1388

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRSeniorMemberCC.story

Scenario: As a RR Senior member with a used saved credit card, I Book a round trip a senior fare,

Given I search for a round-trip flight for 1 senior(s) with Senior fare
When I book a senior fare flight and continue to the purchase page
Then I should not see the Stored Payment Security Code field
When I fill out the purchase page
Then I view my complete itinerary on the confirmation page
When I retrieve my Senior itinerary
Then I view my Senior itinerary