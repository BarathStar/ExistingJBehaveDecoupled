Verify messaging when customer attempts to book a round trip with return after service ends

Meta:
@project coda
@project_in_dev
@flow air
@process booking
@traveler adult
@dyna_stubs
@not_live

@project bookingWidget

Narrative:
In order to show behavior when an Airtran station closes
As a Customer
I want to get an Oops message when the return flight is after service is discontinued


Scenario: An adult searches for an AirTran roundtrip beyond schedule end

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|EYW|
    |arrivalStation|ATL|
    |departureDate|+2|
    |returnDate|+32|

And The following routes are available:
    |Field|Value|
    |originStation|EYW|
    |destinationStation|ATL|
    |carrierDates|FL:F:[today,today+30]|
    |originStation|ATL|
    |destinationStation|EYW|
    |carrierDates|FL:F:[today,today+30]|

And Only the following one way schedule is available from EYW to ATL on +2d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |100    |EYW    |+2d 12:00  |ATL        |+2h    |FL     |
    |2      |200    |EYW    |+5d 12:00  |ATL        |+2h    |FL     |
    |3      |300    |EYW    |+7d 12:00  |ATL        |+2h    |FL     |
And Only the following one way schedule is available from ATL to EYW on +2d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |4      |150    |ATL    |+2d 14:00  |EYW        |+1h30m |FL     |
    |5      |250    |ATL    |+5d 16:00  |EYW        |+1h45m |FL     |
    |6      |350    |ATL    |+7d 18:00  |EYW        |+1h30m |FL     |
And Only the following one way schedule is available from ATL to EYW on +32d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|

When I attempt to search for flights from the home page
Then I see an Oops messages from home page indicating that Southwest Airlines currently does not offer service between (Key West - EYW) and (Atlanta - ATL).
