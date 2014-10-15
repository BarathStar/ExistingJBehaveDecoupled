Attempts to change a flight in progress or after no-show

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@not_passing broken

Narrative: As a passenger that missed my flight
I want to change the missed portion of my itinerary
So that I can see Mom.

Scenario: change no-show
Given I have missed the outbound flight of my itinerary
When I want to change my itinerary
Then I am able to select the inbound flight for change
And I am not able to select the missed flight for change

Scenario: can't change when flight is in progress
Given I have missed the outbound flight of my itinerary and it is in progress
When I want to change my itinerary
Then I should be informed that my flight is in progress

Scenario: Can't change when flight is in progress on swabiz
Given I have anonymously logged into a SWABiz account
And I have checked in for my SWABiz itinerary and it is in progress
When I attempt to change my Swabiz itinerary
Then I should be informed that my flight is in progress
