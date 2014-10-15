User fails to complete the Accompanying Traveler information because the mandatory fields are not entered and decides to cancel

Meta:
@flow air
@process booking
@user anonymous
@traveler adult_minor_child
@dyna_stubs
@project UM/YT

Narrative:
In order to validate the Accompanying Traveler form that I must fill in with the traveling companion for an Infant (pax age: under 5) and a Minor (pax age: 5-11)
As an Anonymous User
I want to be shown the corresponding oops message when I do not enter the mandatory fields and return to the Editable Purchase Page if I do not complete the Form where the credit cards fields are empty

Scenario: User fails to complete the Accompanying Traveler Form for an Infant and a Minor because the mandatory fields are not entered and returns to the Editable Purchase Page after the failure
Given I am flying a round-trip Southwest Southwest flight
And I am an infant traveling with a minor
When I attempt to purchase a flight as an infant
And I confirm there will be a person traveling as a companion on the UM modal
And I attempt to retrieve an accompanying itinerary without entering a mandatory field
Then I see the Oops message indicating that the mandatory fields were not entered
When I cancel the accompanying adult form
Then I see the Editable Purchase page
And I see the credit card fields empty