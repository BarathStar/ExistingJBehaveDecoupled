Purchase a early bird eligible round-trip Anytime NS as an Alist member.

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@storyId MH-1432

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/A-List.story

Scenario: I book a early bird eligible round-trip flight as an Alist memeber where my passengers are Alist preferred and adult pax.

Given I am flying an Early Bird eligible southwest airtran round-trip flight
When I book a flight for passengers Alist, goodUser, AlistPreferred
Then I see the Early Bird upsell button
