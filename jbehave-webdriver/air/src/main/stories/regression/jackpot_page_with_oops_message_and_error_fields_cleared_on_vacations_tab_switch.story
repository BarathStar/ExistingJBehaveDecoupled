Ensures jackpot form fields are cleared on vacations tab switch

Meta:
@suite regression
@flow air
@not_live
@process select
@user anonymous
@story_id BUG-494

Narrative:
When I submit a jackpot form and generate errors, the errors are cleared when switching to the vacations tab

Scenario: Jackpot form submission with missing fields cleared on vacation tab switch

Given I am on the Jackpot page
When I clear the package departure and return dates
And I click on the package search button
Then I see the following package fields have errors: origin, destination, departureDate, returnDate
And I see an oops with the message: No departure airport selected for the outbound flight.
And I see an oops with the message: No arrival airport selected for the inbound flight.
And I see an oops with the message: You did not enter a departure date.
And I see an oops with the message: You did not enter a return date.

Given I am on the Vacations page
Then I see the following fields do not have errors: origin, destination, departureDate, returnDate
And I should not see an oops message
