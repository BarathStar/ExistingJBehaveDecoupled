Infant flying with a YT performs purchase directly

Meta:
@flow air
@process booking
@user anonymous
@traveler child_yt
@dyna_stubs
@project UM/YT

Narrative:
In order to perform the purchase directly
As an anonymous user
I want to book a flight with an Infant (pax age: under 5) and a YT passenger (pax age: 12-17)

Scenario: Infant flying with a YT performs purchase directly, and modal is displayed
Given I am flying a round-trip Southwest Southwest flight
And I am a YT traveling with an infant
When I attempt to purchase a flight as a YT
Then I see the YT modal asking if there will be a companion
When I confirm there will be a person traveling as a companion on the YT modal
Then I see my booked flight on the confirmation page