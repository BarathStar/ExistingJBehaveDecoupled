YT and UM traveling alone do not fill out the Young Traveler Parent/Guardian form and complete the purchase directly

Meta:
@flow air
@process booking
@user anonymous
@traveler yt_um
@dyna_stubs
@project UM/YT

Narrative:
In order to purchase a flight without requiring an Accompanying Traveler or a Parent/Guardian for two passengers
As an Anonymous User
I want to select a Young Traveler (pax age: 12-17) and a Minor (pax age: 5-11) and be able to complete the purchase directly
when I confirm they travel with a companion

Scenario: User can purchase a flight for a Minor and a YT without an Accompanying Traveler or a Parent/Guardian when he/she confirms they travel with companion
Given I am flying a round-trip Southwest Southwest flight
And I am a YT traveling with a minor
When I attempt to purchase a flight as a YT
And I confirm there will be a person traveling as a companion on the YT modal
Then I see my booked flight on the confirmation page