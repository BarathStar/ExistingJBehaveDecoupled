Verify redirect to AirTran.com from home page

Meta:
@process booking
@traveler adult
@dyna_stubs
@not_live
@homepage2off
@not_passing

Narrative:
In order to show the booking flow for AirTran destinations
As a Customer
I want to select an AirTran market pair that are not serviced by southwest.com


Scenario: An adult searches for an AirTran flight on the home page

Given I am on the Homepage
And The following routes are available:
    |Field|Value|
    |originStation|ATL|
    |destinationStation|CUN|
    |carrierDates|FL:F:[today,today+30]|
When On home page I select one-way AirTranOnly trip from ATL to CUN on today
Then view the AirTran Redirect Modal with WCM Content
