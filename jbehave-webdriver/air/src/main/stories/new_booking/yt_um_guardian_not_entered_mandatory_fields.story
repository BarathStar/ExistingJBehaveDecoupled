User fails to complete the YT Parent/Guardian because the mandatory fields are not entered returning to the Editable Purchase Page

Meta:
@flow air
@process booking
@user anonymous
@traveler yt
@dyna_stubs
@project UM/YT

Narrative:
In order to view the YT Parent/Guardian Information when a Minor (pax age: 5-11) and a YT (pax age: 12-17) travel without companion
As an Anonymous User
I want to be shown YT Parent/Guardian Information and return to the Editable Purchase Page when I do not indicate a Guardian for my passengers

Scenario: User fails to complete the YT Parent/Guardian for a Minor and a Young Traveler because the mandatory fields are not entered and returns to the Editable Purchase Page
Given I am flying a round-trip Southwest Southwest flight
And I am a YT traveling with a minor
When I attempt to purchase a flight as a YT
And I confirm there will not be a person traveling as a companion on the YT modal
When I attempt to save the YT Parent/Guardian without entering the mandatory fields
Then I see the Oops messages indicating that all the mandatory fields were not entered on the YT guardian form
When I cancel the YT Parent/Guardian form
Then I see the Editable Purchase page