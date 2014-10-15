Block an Early Bird direct purchase with a PNR from MyIdTravel

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project my_id_travel

Narrative: In order to verify Early Bird
As a user
I want to view an Oops message in the Early Bird when attempt retrieve a MyIDTravel PNR

Scenario: Early Bird Check-in from MyIdTravel

Given I am flying a round-trip Southwest Southwest flight
When I have a MyIdTravel flight booked
And I am on the early bird page
And I try to retrieve itinerary on the Early Bird check-in page
Then I see the My Id Travel Oops message on EarlyBird page