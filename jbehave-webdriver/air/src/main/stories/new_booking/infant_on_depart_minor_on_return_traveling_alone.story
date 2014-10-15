UM - Infant on departure who is an UM on return views an Oops message when traveling alone

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to not be allowed to travel alone
As an anonymous user
I want to be shown an oops message
when I book a flight with an Infant (pax age: under 5) on departure who is a Minor (pax age: 5-11)  on return

Scenario: Infant on departure who is an UM on return views OOPS message
Given I am flying a round-trip Southwest Southwest flight
And I am an infant on the depart and a minor on the return
When I attempt to purchase a flight as an infant
And I confirm there will not be a person traveling as a companion on the UM modal
Then I view an Oops message stating that infants are not permitted to fly unaccompanied