View flight schedules on the Flight Schedules page.

Meta:
@flow air
@process flight_status
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to view flight schedules on the Flight Schedules page
As a user
I want to enter origin and destination stations
So that I can view flight schedules for the week

Scenario: View weekly schedule
Given I am on the flight schedule page
When I search for a weekly schedule from DAL to LGA 3 days from today
Then I view a table of flight options from DAL to LGA week starting 3 days from today
