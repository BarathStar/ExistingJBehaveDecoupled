SWABiz Anonymous air purchase

Meta:
@flow air
@process booking
@user sb_anonymous
@traveler adult
@not_passing draft

Narrative: In order to use SWABiz Anonymous air purchase for only Southwest segments
As a SWABiz Anonymous customer
I want to book a round trip
so that I can see the confirmation page

Scenario: SWABiz Anonymous air purchase for only WN segments
Given I am flying a round-trip Southwest Southwest flight
And I have anonymously logged into a SWABiz account
When I search for a flight
And I select and purchase a flight
Then I should see the SWABiz confirmation page
