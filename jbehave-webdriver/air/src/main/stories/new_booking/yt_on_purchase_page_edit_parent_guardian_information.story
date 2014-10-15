View Parent/Guardian Information and change it

Meta:
@flow air
@process booking
@user anonymous
@traveler yt
@UM
@dyna_stubs



Narrative:
In order to check the information of the YT Parent/Guardian I have previously entered and change it
As an Anonymous User
I want to be shown the information for the YT Parent/Guardian when I click on the Edit Parent Guardian information link
located in the Purchase Page, edit this information and be shown this change on the YT Parent/Guardian popup

Scenario: User views the YT Parent/Guardian information previously entered and changes it
Given I am flying a round-trip Southwest Southwest flight
And I am a YT traveling alone
When I attempt to purchase a flight as a YT
And I confirm there will not be a person traveling as a companion on the YT modal
Then I verify YT policies travelling alone message is displayed
When I complete the YT Parent/Guardian Information with Call notification method
And I attempt to edit the Parent/Guardian information on the Non-Editable Purchase page
Then I see the Parent/Guardian Contact Information previously entered on the YT Parent/Guardian Form
And I see the same itinerary selected during the booking on the Young Traveler purchase page
When I change the YT Parent/Guardian Information with Text notification method
And I view the Parent/Guardian Information on the Non-Editable Purchase page
Then I see the YT guardian information edited and completed
