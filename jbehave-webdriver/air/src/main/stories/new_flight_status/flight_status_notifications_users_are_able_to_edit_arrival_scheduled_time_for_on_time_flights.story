Verify that Southwest.com users are able to edit arrival flight status notifications up to the flight's scheduled time for on time flights

Meta:
@flow air
@process flight_status
@traveler adult
@user anonymous
@global_nav_regression
@dyna_stubs
@not_live

Narrative:
As a southwest .com user changing an an existing arrival notification
I want to be able to subscribe up to the scheduled arrival time when my flight is ontime
So that I can have updated my notification corresponding to my ontime flight

Scenario: Verify that Southwest.com users are able to edit arrival flight status notifications up to the flight's scheduled time for on time flights
Given I have the following itinerary data:
|Field|Value|
|departureStation|DAL|
|arrivalStation|HOU|
|departingFlight_number|6891|
|departureDate|+0|
|departureTimeInMinutes|+5|
And I am on the View or Edit Notification tab on Flight Status page
And I have retrieved an exiting arrival notification
And I enter the information corresponding to an ontime flight within 5 minutes of the flight's scheduled arrival time
Then I see that my arrival notification was successfully updated

