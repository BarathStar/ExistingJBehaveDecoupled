User fails to complete the UM Parent/Guardian by indicating the same person for the primary and alternate
pick-up and returns to the Editable Purchase Page after the failure.

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to validate the UM Parent/Guardian Form
As an Anonymous User
I want to be shown an oops message when I enter the same person for the primary and the alternate pick-up and return to the Editable Purchase Page when I do not complete the form

Scenario: User fails to complete the UM Parent/Guardian for a Minor because the same person for the primary and alternate pick-up is entered on the form and returns to the Editable Purchase Page after the failure.
Given I am flying a one-way SouthwestNS flight with a Nonstop segment
And I am a minor traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Parent/Guardian Information with the same primary and alternate pick-up person for the flight
And I attempt to save the UM Parent/Guardian information
Then I see an oops message which indicates that the same person cannot be both primary and alternate parent/guardian for the departing flight
When I cancel the UM Guardian form
Then I see the Editable Purchase page