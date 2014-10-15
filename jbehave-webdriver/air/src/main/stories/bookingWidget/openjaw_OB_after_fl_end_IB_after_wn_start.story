Verify messaging when open jaw is booked across service start boundary.

Meta:
@project coda
@project_in_dev
@flow air
@process booking
@traveler adult
@dyna_stubs
@not_passing
@draft broken until new booking widget on select flight page
@not_live

@project bookingWidget
@note This test currently works against live service.  We add @not_live in case the test fails due to
@that ATL-AUA is converted to WN markets.

Narrative:
In order to show the booking flow for Southweest destinations
As a Customer
I want to get an Oops message when I book a round trip with different carriers


Scenario: An adult searches for an AirTran roundtrip beyond schedule end

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|EYW|
    |arrivalStation|BHM|
    |returnStation|MCO|
    |departureDate|+12|
    |returnDate|+20|
And Only the following one way schedule is available from EYW to BHM on +0d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |100    |EYW    |+2d 12:00  |BHM        |+2h    |FL     |
    |2      |200    |EYW    |+2d 12:00  |BHM        |+2h    |FL     |
    |3      |300    |EYW    |+2d 12:00  |BHM        |+2h    |FL     |
And Only the following one way schedule is available from ATL to MCO on +18d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |4      |150    |BHM    |+2d 14:00  |MCO        |+1h30m |WN     |
    |5      |250    |BHM    |+2d 16:00  |MCO        |+1h45m |WN     |
    |6      |350    |BHM    |+2d 18:00  |MCO        |+1h30m |WN     |

And The following routes are available:
    |Field|Value|
    |originStation|EYW|
    |destinationStation|BHM|
    |carrierDates|FL:F:[today,today+10]|
And The following routes are available:
    |Field|Value|
    |originStation|BHM|
    |destinationStation|MCO|
    |carrierDates|WN:T:[today+15,today+30]|

When I am on the select flights page
Then I see an Oops messages from select flight page indicating that Southwest Airlines currently does not offer service between (Key West - EYW) and (Birmingham - BHM).
