User does not select Early Bird Check-in when booking a flight for an UM but he/she is informed that Unaccompanied Minors are not eligible for EarlyBird Check-In

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to validate EB feature for Unaccompanied Minors (pax age: 5-11)
As an anonymous user
I want to be informed that Unaccompanied Minors are not eligible for EarlyBird

Scenario: User does not select Early Bird Check-in when booking a flight for an UM but he/she is informed that Unaccompanied Minors are not eligible for EarlyBird Check-In
Given I am flying a round-trip Southwest flight EB eligible with Nonstop segments
And I am a minor traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Guardian information for the round-trip itinerary
Then I see a message indicating that UMs are not eligible for EB checkin