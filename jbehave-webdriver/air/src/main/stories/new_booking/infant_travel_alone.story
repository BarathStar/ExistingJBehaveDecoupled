Verify Infants aren't allowed to travel alone

Meta:
@flow air
@process booking
@user anonymous
@traveler child
@project UM/YT
@dyna_stubs

Narrative:
In order to validate purchases for Infant Passengers
As an anonymous user
I want to be shown an error message if Infant Passenger (pax age: under 5) travels alone

Scenario: Infants aren't allowed to travel alone
Given I am flying a round-trip Southwest Southwest flight
And I am an infant traveling alone
When I attempt to purchase a flight as an infant
And I confirm there will not be a person traveling as a companion on the UM modal
Then I view an Oops message stating that infants are not permitted to fly unaccompanied