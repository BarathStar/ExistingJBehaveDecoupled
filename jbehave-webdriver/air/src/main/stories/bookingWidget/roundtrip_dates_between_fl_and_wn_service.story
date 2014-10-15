Verify messaging when searching for trip between Airtran and Southwest schedules

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
I want to get an Oops message when I book a round trip during the period neither carrier offers service


Scenario: An adult searches for an AirTran roundtrip beyond schedule end

Given I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|EYW|
    |arrivalStation|MSY|
    |departureDate|+12|
    |returnDate|+15|
And Only the following one way schedule is available from EYW to MSY on +0d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |100    |EYW    |+2d 12:00  |MSY        |+2h    |FL     |
    |2      |200    |EYW    |+2d 12:00  |MSY        |+2h    |FL     |
    |3      |300    |EYW    |+2d 12:00  |MSY        |+2h    |FL     |
And Only the following one way schedule is available from MSY to EYW on +18d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |4      |150    |MSY    |+2d 14:00  |EYW        |+1h30m |WN     |
    |5      |250    |MSY    |+2d 16:00  |EYW        |+1h45m |WN     |
    |6      |350    |MSY    |+2d 18:00  |EYW        |+1h30m |WN     |

And The following routes are available:
    |Field|Value|
    |originStation|EYW|
    |destinationStation|MSY|
    |carrierDates|FL:F:[today,today+10]|
    |carrierDates|WN:T:[today+20,today+30]|
And The following routes are available:
    |Field|Value|
    |originStation|MSY|
    |destinationStation|EYW|
    |carrierDates|FL:F:[today,today+10]|
    |carrierDates|WN:T:[today+20,today+30]|

When I attempt to search for flights from the home page
Then I see an Oops messages from home page indicating that Published scheduled service between (Key West - EYW) and (New Orleans - MSY) does not begin
