Verify messaging when return flight is after Airtran service discontinued but valid on Southwest

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
I want to get an Oops message when I book a round trip with different carriers across the boundary


Scenario: An adult searches for a roundtrip when service has changed reservation systems

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|EYW|
    |arrivalStation|PHL|
    |departureDate|+2|
    |returnDate|+18|
And Only the following one way schedule is available from EYW to PHL on +0d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |100    |EYW    |+2d 12:00  |PHL        |+2h    |FL     |
    |2      |200    |EYW    |+5d 12:00  |PHL        |+2h    |FL     |
    |3      |300    |EYW    |+7d 12:00  |PHL        |+2h    |FL     |
And Only the following one way schedule is available from PHL to EYW on +16d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |4      |150    |PHL    |+2d 14:00  |EYW        |+1h30m |WN     |
    |5      |250    |PHL    |+3d 16:00  |EYW        |+1h45m |WN     |
    |6      |350    |PHL    |+7d 18:00  |EYW        |+1h30m |WN     |

And The following routes are available:
    |Field|Value|
    |originStation|EYW|
    |destinationStation|PHL|
    |carrierDates|FL:F:[today,today+15]|
    |carrierDates|WN:T:[today+20,today+30]|
And The following routes are available:
    |Field|Value|
    |originStation|PHL|
    |destinationStation|EYW|
    |carrierDates|FL:F:[today,today+15]|
    |carrierDates|WN:T:[today+20,today+30]|

When I attempt to search for flights from the home page
Then I see an Oops messages from home page indicating that Published scheduled service between (Key West - EYW) and (Philadelphia - PHL) does not begin
