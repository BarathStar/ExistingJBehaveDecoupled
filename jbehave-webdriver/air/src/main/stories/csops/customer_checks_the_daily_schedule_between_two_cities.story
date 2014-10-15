Check the daily flights for a City Pair, so that I see their departure and arrival times, number of stops and whether they are on time or delayed.

Meta:
@flow air
@process flight_status
@user anonymous
@traveler adult


Narrative:
As an anonymous user
I want to start the city pair schedules checking process, so I am shown the departure and arrival times, number of stops and whether they are on time or delayed
In order to be able to view the information related to the daily flights for a certain City pair

Scenario: Customer checks the daily flights schedule available between two cities
Given I have the following itinerary data:
    |Field|Value|
    |departureStation|SAT|
    |arrivalStation|DAL|

When I am on the flight schedule page
And I search for a daily flight schedule
Then I see the daily flight schedules results for the given itinerary
