View weekly flight schedules for Southwest/FL

Meta:
@suite newEcomSchedules
@flow air
@process flight schedules
@user anonymous
@dyna_stubs
@not_live

Narrative:

In order to find a desirable date to fly
As an anonymous user
I want to weekly view schedules for WN/FL(ATL) eastern time zone route

Scenario: Enter a Southwest itinerary on the flight status results page

Given I have the following itinerary data:
    |Field|Value|
    |departureStation|DAL|
    |arrivalStation|ATL|
    |departureDate|+11|

Given Only the following weekly schedule is available from DAL to ATL on week starting Monday for day +11d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |1628   |DAL    |+11d 06:15 |HOU        |+0d 07:30 |WN  |
    |1      |1628   |       |+0d  08:00 |ATL        |+0d 10:30 |WN  |
    |2      |481    |DAL    |+0d 08:45  |SAT        |+0d 09:50 |WN  |
    |2      |5521   |       |+0d  11:10 |ATL        |+0d 14:22 |FL  |

And I am on the flight schedule page
When I search for a weekly flight schedule
Then I should see that flight number 481 has departure time of 08:45 AM and arrival time of 03:22 PM
And I should see weekly flight 5521 Operated by AirTran



