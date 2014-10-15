View daily flight schedules for Southwest/FL

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
I want to daily view schedules for FL(EYW)/WN eastern time zone route

Scenario: Enter a Southwest itinerary on the flight status results page

Given I have the following itinerary data:
    |Field|Value|
    |departureStation|EYW|
    |arrivalStation|PHL|
    |departureDate|+11|

Given Only the following one way schedule is available from EYW to PHL on +11d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |937    |EYW    |+11d 12:00 |MCO        |+0d 14:30 |FL  |
    |1      |1049   |       |+0d  13:00 |PHL        |+0d 14:45 |WN  |
    |2      |100    |EYW    |+0d 14:00  |MCO        |+0d 16:30 |FL  |
    |2      |200    |       |+0d  17:00 |PHL        |+0d 18:00 |WN  |

And I am on the flight schedule page
When I search for a daily flight schedule
Then I should see that flight number 937 has departure time of 01:00 PM and arrival time of 03:45 PM
And I should see daily flight 937 Operated by AirTran
And I should see daily flight 100 Operated by AirTran


