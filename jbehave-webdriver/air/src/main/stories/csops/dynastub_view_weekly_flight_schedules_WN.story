View daily flight schedules for Southwest

Meta:
@suite newEcomSchedules
@flow air
@process flight schedules
@user anonymous


Narrative:

In order to find a desirable date to fly
As an anonymous user
I want to weekly view schedules for a given route

Scenario: Enter a Southwest itinerary on the flight status results page

Given I have the following itinerary data:
    |Field|Value|
    |departureStation|SAT|
    |arrivalStation|DAL|
    |departureDate|+90|

And The following schedule is available on the select flights page:
    |command|parameter|
    |origin|SAT|
    |destination|DAL|
    |daysOutFromToday|90|
    |dynaPost|flightSchedules|
    |segment||
    |originDestination||
When I am on the flight schedule page
And I search for a weekly flight schedule
Then I see the weekly flight schedules results for the given itinerary
