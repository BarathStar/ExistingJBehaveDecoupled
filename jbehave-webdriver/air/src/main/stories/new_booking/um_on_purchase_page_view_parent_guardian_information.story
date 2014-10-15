Edit Passenger Information button and View/Edit Parent/Guardian Information links functionality

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to check the information of the UM Parent/Guardian I have previously entered for a Minor passenger (pax age: 5-11) and return to the Editable Purchase Page
As an anonymous user
I want to be shown the information previously entered for a UM Parent/Guardian
when I click on the View Parent/Guardian Information link located in the Non-editable Purchase Page and be redirected to the Editable Purchase Page
to change the passenger information

Scenario: User checks the UM Parent/Guardian information and returns to the Editable Purchase Page
Given I am flying a one-way SouthwestNS flight with a Nonstop segment
And I am a minor traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Guardian information for the one-way itinerary
And I continue from the UM Guardian page to the Non-Editable Purchase page
And I view the Parent/Guardian Information on the Non-Editable Purchase page
Then I see the UM Guardian information previously completed
When I click on the Edit Passenger Information button on the Non-Editable Purchase Page
Then I see the Editable Purchase page