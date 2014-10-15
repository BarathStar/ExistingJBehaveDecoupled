Block an attempt to Cancel flight as an SWwaBiz User with a PNR from MyIdTravel

Meta:
@flow air
@process cancel
@user sb_user
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative:
In order to receive an Oops message for a MyID Travel PNR
As a SwaBiz User
I want to attempt to checking for a flight

Scenario: Block MyIDTravel SWABiz User air cancel

Given I have anonymously logged into a SWABiz account
And I am flying a round-trip Southwest Southwest flight
And I have a MyIdTravel flight booked
When I attempt to cancel my Swabiz itinerary
Then I see the My Id Travel Oops message on cancel air page