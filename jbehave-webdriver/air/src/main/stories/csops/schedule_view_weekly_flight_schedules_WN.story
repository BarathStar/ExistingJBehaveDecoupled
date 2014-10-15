View weekly flight schedules for Southwest

Meta:
@suite newEcomSchedules
@flow air
@process flight schedules
@user anonymous
@project_in_dev
@dyna_stubs
@not_live

Narrative:

In order to find a desirable date to fly
As an anonymous user
I want to weekly view schedules for a given route

Scenario: Enter a Southwest itinerary on the flight status results page

Given I have the following itinerary data:
    |Field|Value|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departureDate|+11|

Given Only the following weekly schedule is available from HOU to DAL on week starting Monday for day +11d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |100    |HOU    |+11d 12:00 |DAL        |+0d 14:30 |WN  |
    |2      |200    |HOU    |+0d  13:00 |DAL        |+0d 14:45 |WN  |
    |3      |300    |HOU    |+0d  14:00 |DAL        |+0d 15:00 |WN  |


And I am on the flight schedule page
When I search for a weekly flight schedule
Then I should see that flight number 100 has departure time of 12:00 PM and arrival time of 02:30 PM
And I should see that flight number 200 has departure time of 01:00 PM and arrival time of 02:45 PM
And I should see that flight number 300 has departure time of 02:00 PM and arrival time of 03:00 PM
