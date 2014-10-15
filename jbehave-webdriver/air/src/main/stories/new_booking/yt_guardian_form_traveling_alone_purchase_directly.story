The user can purchase a flight directly for a YT if she/he travels with companion

Meta:
@flow air
@process booking
@user anonymous
@traveler yt
@dyna_stubs
@project UM/YT

Narrative:
In order to purchase a flight directly for a passenger
As an Anonymous User
I want to select a Young Traveler (pax age: 12-17) and complete the purchase directly when I confirm he/she travels with companion

Scenario: User can purchase a flight for a YT if she/he travels with companion
Given I am flying a round-trip Southwest Southwest flight
And I am a YT traveling alone
When I attempt to purchase a flight as a YT
And I confirm there will be a person traveling as a companion on the YT modal
Then I see my booked flight on the confirmation page