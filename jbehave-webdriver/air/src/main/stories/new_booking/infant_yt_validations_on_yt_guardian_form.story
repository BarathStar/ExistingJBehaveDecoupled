Infants flying with YTs: Young Traveler Parent/Guardian form validations

Meta:
@flow air
@process booking
@traveler yt
@user anonymous
@dyna_stubs
@project UM/YT
@not_passing takestoolongtorun

Narrative:
In order to purchase a flight for an infant (pax age: under 5) and a young traveler (pax age: 12-17) who travel without companion
As an anonymous user
I want to be able to complete a Parent/Guardian for my passengers and perform the purchase

Scenario:
User completes the purchase for an Infant and a Young Traveler who travel alone after indicating the
information for a Parent/Guardian
Given I am flying a round-trip Southwest Southwest flight
And I am a YT traveling with an infant
When I attempt to purchase a flight as a YT
And I confirm there will not be a person traveling as a companion on the YT modal
And I complete the YT guardian information
Then I see the Non-Editable Purchase page
When I complete the credit card information and finish the purchase
Then I see my booked flight on the confirmation page