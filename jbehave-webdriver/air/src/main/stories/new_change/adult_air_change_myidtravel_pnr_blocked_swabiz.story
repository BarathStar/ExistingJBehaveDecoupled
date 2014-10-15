Block an attempt to Change existing adult flight itinerary  on Swabiz with a PNR from MyIdTravel

Meta:
@flow air
@process change
@user sb_user
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative:
In order to receive an Oops message for a MyID Travel PNR
As a SwaBiz User
I want to attempt to change my flight itinerary on Swabiz

Scenario: Block MyIDTravel Air Change on Swabiz

Given I have anonymously logged into a SWABiz account
And I am flying a round-trip Southwest Southwest flight
When I have a MyIdTravel flight booked
And I attempt to change my Swabiz itinerary
Then I see the My Id Travel Oops message on Change page