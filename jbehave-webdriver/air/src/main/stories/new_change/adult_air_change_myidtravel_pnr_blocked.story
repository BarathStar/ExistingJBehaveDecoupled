Block an attempt to Change existing adult flight itinerary with a PNR from MyIdTravel

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative:
In order to receive an Oops message for a MyID Travel PNR
As an adult
I want to attempt to change my flight itinerary

Scenario: Block MyIDTravel Air Change

Given I am flying a round-trip Southwest Southwest flight
When I have a MyIdTravel flight booked
And I attempt to change my flight itinerary
Then I see the My Id Travel Oops message on Change page