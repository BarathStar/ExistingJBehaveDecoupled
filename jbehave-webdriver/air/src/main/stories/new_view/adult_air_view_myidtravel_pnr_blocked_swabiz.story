Block an attempt to view an itinerary as an SwaBiz User with a PNR from MyIdTravel

Meta:
@flow air
@process view
@user sb_user
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative:
In order to receive an Oops message for a MyID Travel PNR
As a SwaBiz User
I want to view my air itinerary an receive an Oops message

Scenario: Block MyIDTravel SwaBiz User air view

Given I have anonymously logged into a SWABiz account
And I am flying a round-trip Southwest Southwest flight
And I have a MyIdTravel flight booked
When I retrieve my SwaBiz itinerary
Then I see the My Id Travel Oops message on view itinerary page