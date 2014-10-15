Verify go to BUG page from home page

Meta:
@project coda
@project_in_dev
@flow air
@process booking
@traveler adult
@dyna_stubs
@not_live
@homepage2
@project bookingWidget

Narrative:
In order to show the booking flow for Southwest destinations
As a Customer
I want to select an Southwest market pair that are serviced by southwest.com


Scenario: An adult searches for an Southwest flight on the home page

Given I am on the Homepage
And The following routes are available:
    |Field|Value|
    |originStation|DAL|
    |destinationStation|AUS|
    |carrierDates|WN:T:[today,today+60]|
    |carrierDates|FL:F:[today,today+60]|
When On home page I select one-way SouthwestNS trip from DAL to AUS on today
Then Verify that I am on the Select Flights page
