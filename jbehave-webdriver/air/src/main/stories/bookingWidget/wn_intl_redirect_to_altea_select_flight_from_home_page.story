Verify go to Altea web site from home page

Meta:
@project coda
@project_in_dev
@flow air
@process booking
@traveler adult
@not_live
@dyna_stubs
@not_passing draft

@project bookingWidget
@note this test needs to have Altea web server running

Narrative:
In order to show the booking flow for Southwest International destinations
As a Customer
I want to select an Southwest International market pair that are serviced by global.southwest.com


Scenario: An adult searches for an Southwest International flight on the home page

Given I am on the Homepage
And The following routes are available:
    |Field|Value|
    |originStation|HOU|
    |destinationStation|GDL|
    |carrierDates|WN:F:[today,today+60]|
When On home page I select one-way SwaInternationalRoute trip from HOU to GDL on today
Then Verify that correct redirect url is created for Altea web site
