Ensures vacations form fields are cleared on jackpot tab switch

Meta:
@suite regression
@flow air
@not_live
@process select
@user anonymous
@story_id BUG-494

Narrative:
When I submit a vacations form and generate errors, the errors are cleared when switching to the jackpot tab

Scenario: Vacations form submission with missing fields cleared on jackpot tab switch

Given I am on the Vacations page
When I clear the departure and return dates
And I click on the search button
Then I see the following fields have errors: origin, destination, departureDate, returnDate
And I see an oops with the message: No departure airport selected for the outbound flight.
And I see an oops with the message: No arrival airport selected for the inbound flight.
And I see an oops with the message: You did not enter a departure date.
And I see an oops with the message: You did not enter a return date.

Given I am on the Jackpot page
Then I see the following package fields do not have errors: origin, destination, departureDate, returnDate
And I should not see an oops message