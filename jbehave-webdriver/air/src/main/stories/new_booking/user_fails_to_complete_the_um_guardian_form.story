User fails to complete the UM Parent/Guardian Form because the mandatory fields are not entered

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to be shown the validations for all the mandatory fields on the UM Parent/Guardian Form
As an Anonymous User
I want to be shown the corresponding oops messages when I do not complete the mandatory fields on the UM Parent/Guardian Information

Scenario: User fails to complete the UM Parent/Guardian Form because the mandatory fields are not entered
Given I am flying a round-trip SouthwestNS flight with Nonstop segments
And I am a minor traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
And I attempt to save the UM Parent/Guardian information
Then I see the Oops messages indicating that all the mandatory fields were not entered
And I see the options phone and text as notification methods on the UM Parent/Guardian Form
When I complete the UM Guardian information for the one-way itinerary
And I choose to prepopulate the fields for the Returning Flight based on the Departing Flight
Then I see the departing contact information on the returning flight section on the UM Guardian form
When I continue from the UM Guardian page to the Non-Editable Purchase page
Then I see an Oops messages indicating that the Alternate contact for the returning flight must be completed
