Block an attempt to Checkin flight as an Adult with a PNR from MyIdTravel

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative:
In order to receive an Oops message for a MyID Travel PNR
As an adult
I want to attempt to checking for a flight

Scenario: Block MyIDTravel Adult CheckIn

Given I am flying a round-trip Southwest Southwest flight
When I have a MyIdTravel flight booked
And I proceed to the new online checkin page
Then I see the My Id Travel Oops message on check in page