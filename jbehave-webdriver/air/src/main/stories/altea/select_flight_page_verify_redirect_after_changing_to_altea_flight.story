Verify redirect url to Altea web site from select flights page

Meta:
@project coda
@project_in_dev
@flow air
@process booking
@traveler adult
@not_live
@dyna_stubs

Narrative:
In order to show the booking flow for Southwest International destinations
As a Customer
I want to select an Southwest International market pair that are serviced by ALTEA

Scenario: An adult modifys the search to an Southwest International flight on the select page

Given The following routes are available:
    |Field|Value|
    |originStation|DAL|
    |destinationStation|SAT|
    |carrierDates|WN:F:[today,today+60]|
And I am a customer on the select flights page traveling from DAL to HOU
And I am changing the following itinerary data to:
    |Field|Value|
    |departureStation|DAL|
    |arrivalStation|SAT|
When I modify my search for new stations
Then Verify that correct redirect url is created for Altea web site