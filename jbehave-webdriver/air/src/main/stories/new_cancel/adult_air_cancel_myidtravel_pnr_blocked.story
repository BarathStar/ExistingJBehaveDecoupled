Block an attempt to Cancel flight as an adult with a PNR from MyIdTravel

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative:
In order to receive an Oops message for a MyID Travel PNR
As an adult
I want to cancel a flight and see an Oops message

Scenario: Block MyIDTravel adult air cancel

Given I am flying a round-trip Southwest Southwest flight
When I have a MyIdTravel flight booked
And I attempt to cancel the flight
Then I see the My Id Travel Oops message on cancel air page