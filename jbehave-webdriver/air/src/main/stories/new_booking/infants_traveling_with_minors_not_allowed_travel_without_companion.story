Verify Infants traveling with minors are not allowed to travel without companion

Meta:
@flow air
@process booking
@user anonymous
@traveler child_minor
@project UM/YT
@storyId SWACOMTT-1093
@dyna_stubs

Narrative:
In order to book a flight for an Infant (pax age: under 5) and a Minor (pax age: 5-11)
As an anonymous user
I want to be shown an error message if they travel alone.

Scenario: Infants traveling with a Minor aren't allowed to travel alone
Given I am flying a round-trip Southwest Southwest flight
And I am an infant traveling with a minor
When I attempt to purchase a flight as an infant
And I confirm there will not be a person traveling as a companion on the UM modal
Then I view an Oops message stating that infants are not permitted to fly accompanied by minors