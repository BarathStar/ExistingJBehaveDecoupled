Check the flights for a City Pair, so that I see their departure and arrival times, number of stops and whether they are on time or delayed.

Meta:
@flow air
@process flight_status
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As an anonymous user
I want to start the city pair checking process, so I am shown the departure and arrival times, number of stops and whether they are on time or delayed
In order to be able to check the information related to the flights for a certain City pair

Scenario: Customer checks the flights available between two cities
Given I have the following itinerary data:
    |Field|Value|
    |departureStation|SAT|
    |arrivalStation|DAL|

And I am in the Southwest Airlines Flight Status Information site
When I search for the flight status
Then I see the flight status results for the given itinerary
