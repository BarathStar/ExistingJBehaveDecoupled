SJU-NS-OJ with layover less than 4hrs is NOT  prevented to continue on select flight page.

Meta:
@bugpod
@flow air
@process booking
@user rr_member
@traveler adult
@storyId MH-1413

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMember.story

Scenario: Book an open-jaw with less than 4-hour layover

Given I search for an open-jaw flight with Anytime fare
When I book an OpenJaw flight with less than 4 hour layover
Then I view my complete itinerary on the confirmation page