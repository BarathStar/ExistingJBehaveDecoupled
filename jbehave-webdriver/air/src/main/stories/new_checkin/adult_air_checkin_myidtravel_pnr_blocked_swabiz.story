Block an attempt to Checkin flight as an SwaBiz User with a PNR from MyIdTravel

Meta:
@flow air
@process checkin
@user sb_user
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative:
In order to receive an Oops message for a MyID Travel PNR
As a SwaBiz User
I want to attempt to checking for a flight

Scenario: Block MyIDTravel SWABiz User air checkin

Given I have anonymously logged into a SWABiz account
And I am flying a round-trip Southwest Southwest flight
And I have a MyIdTravel flight booked
When I attempt to check in my Swabiz itinerary
Then I see the My Id Travel Oops message on check in page