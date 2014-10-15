Shortcut Enhancement - Several Quick Hits - Search Flight Page

Meta:
@flow other
@process booking
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to validate several quick links enhancement in the low fare calendar page
as a user
I want to search for the lowest fares by month


Scenario: Same month served by 2 carrier

Given I have the following itinerary data:
    |Field|Value|
    |departureStation|STL|
    |arrivalStation|CLT|
    |departureMonth|+1/1|
    |returnMonth|+2/1|

And Only the following one way flight schedule is available from STL to CLT on +1/1 12:00:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |100    |STL    |+1/1 12:00 |CLT        |+2h    |FL     |
    |2      |200    |STL    |+6d 12:00  |CLT        |+2h    |FL     |
    |3      |300    |STL    |+3d 12:00  |CLT        |+2h    |FL     |

And Only the following one way flight schedule is available from STL to CLT on +1/11 12:30:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |4      |150    |STL    |+1/11 12:00  |CLT      |+1h30m |WN     |
    |5      |250    |STL    |+3d 16:00    |CLT      |+1h45m |WN     |
    |6      |350    |STL    |+6d 18:00    |CLT      |+1h30m |WN     |

And Only the following one way flight schedule is available from CLT to STL on +2/1 12:00:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |7      |450    |CLT    |+2/1 12:00   |STL      |+1h30m |WN     |
    |8      |550    |CLT    |+3d 16:00    |STL      |+1h45m |WN     |
    |9      |650    |CLT    |+26d 18:00   |STL      |+1h30m |WN     |
    |10     |750    |CLT    |+1d 18:00    |STL      |+1h30m |WN     |
    |11     |850    |CLT    |+1d 18:00    |STL      |+1h30m |WN     |

And The following one way routes are available:
    |Field|Value|
    |originStation|STL|
    |destinationStation|CLT|
    |carrierDates|FL:F:[+1/1 ,+9d]|
    |carrierDates|WN:T:[+1/11 ,+18d]|

And The following one way routes are available:
    |Field|Value|
    |originStation|CLT|
    |destinationStation|STL|
    |carrierDates|WN:T:[+2/1,+31d]|

When I search flight that served by WN/FL carrier from low fare calender page
Then I should see schedule not started until  Note: Published scheduled service between (St. Louis - STL) and (Charlotte - CLT) does not begin until
