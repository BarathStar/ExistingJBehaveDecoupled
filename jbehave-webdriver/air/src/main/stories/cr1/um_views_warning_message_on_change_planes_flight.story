UM views warning message when traveling on flights that have stops or that require a change of planes

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT
@airTranOnly

Narrative:
In order to view a warning message for a Minor (pax age: 5-11) who travels
As an Anonymous User
I want to book a flight with stops or change of planes

Scenario: Minors cannot fly unaccompanied unless they are flying nonstop or direct flights
Given I am flying a round-trip SouthwestCodeshare flight with Change Planes segments
And I am a minor traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
Then I view an Oops message stating that UMs are not permitted to travel on flights with plane change
