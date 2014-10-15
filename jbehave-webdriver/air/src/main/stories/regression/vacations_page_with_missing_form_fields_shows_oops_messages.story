Ensures vacations form fields are validated on server before posting to external site

Meta:
@suite regression
@flow air
@not_live
@process select
@user anonymous
@story_id BUG-494

Narrative:
When I submit a vacations form, all fields are validated on server before posting to external site

Scenario: Vacations form submission with missing fields

Given I am on the Vacations page
When I clear the departure and return dates
And I click on the search button
Then I see the following fields have errors: origin, destination, departureDate, returnDate
And I see an oops with the message: No departure airport selected for the outbound flight.
And I see an oops with the message: No arrival airport selected for the inbound flight.
And I see an oops with the message: You did not enter a departure date.
And I see an oops with the message: You did not enter a return date.