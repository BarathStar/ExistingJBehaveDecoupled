View the information of my Parent/Guardian previously entered so that I can check this information and be able to return to the previous page when I decide not to edit this information

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to check the information of the UM Parent/Guardian I have previously entered and be able to edit it
As an Anonymous User
I want to be shown the information entered for the UM Parent/Guardian when I click on the Edit Parent/Guardian
information link located in the Non-Editable Purchase Page and return to the Non-Editable Purchase Page
if I decide not to edit the Parent/Guardian Information

Scenario: User views the UM Parent/Guardian information by clicking on the Edit Parent/Guardian information
link and returns to the Non-Editable Purchase Page when decides not to change this information
Given I am flying a one-way SouthwestNS flight with a Nonstop segment
And I am a minor traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Guardian information for the one-way itinerary
And I continue from the UM Guardian page to the Non-Editable Purchase page
And I attempt to edit the Parent/Guardian information on the Non-Editable Purchase page
Then I see the Total UM Charge, the itinerary and the UM Guardian information previously entered
And I should not see the Contact information for the returning flight
When I cancel the UM Guardian form
Then I see the Non-Editable Purchase page