Infant, UM and YT fly with an Adult and complete the purchase directly

Meta:
@flow air
@process booking
@user anonymous
@traveler adult_yt_minor_child
@dyna_stubs
@project UM/YT

Narrative:
In order to complete the purchase directly
As an anonymous user
I want to book a flight with an Adult, young traveler (pax age: 12-17), minor (pax age: 5-11) and infant (pax age: under 5)

Scenario: Infant, UM and YT fly with an Adult and complete the purchase directly, no modal is displayed
Given I am flying a round-trip Southwest Southwest flight
And I am an adult traveling with an infant, a minor and a YT
When I purchase a flight
Then I see my booked flight on the confirmation page