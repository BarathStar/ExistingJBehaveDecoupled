View flight schedules on the Flight Schedules page.

Meta:
@flow air
@process flight_status
@user anonymous
@global_nav_regression
@dyna_stubs

Narrative:
In order to view flight schedules on the Flight Schedules page
As a user
I want to see flight schedules
So that I can see flight options for a day

Scenario: View daily schedule
Given Only the following one way flight schedule is available from DAL to MDW on +2d:
    |ond    |flight |origin |departure  |destination|arrival|carrier|
    |1      |100    |DAL    |+02d 12:00 |MDW        |+0d 14:30 |WN  |
    |2      |200    |DAL    |+0d  13:00 |MDW        |+0d 14:45 |WN  |
    |3      |300    |DAL    |+0d  14:00 |MDW        |+0d 15:00 |WN  |
    |4      |150    |DAL    |+0d  12:20 |MDW        |+0d 14:30 |WN  |
    |5      |250    |DAL    |+0d  14:55 |MDW        |+0d1h     |WN  |
    |6      |350    |DAL    |+0d  14:00 |MDW        |+0d 15:00 |WN  |

And I am on the flight schedule page
When I search for a daily schedule from DAL to MDW 2 days from today
Then I view the daily schedule with flights from DAL to MDW for 2 days from today
