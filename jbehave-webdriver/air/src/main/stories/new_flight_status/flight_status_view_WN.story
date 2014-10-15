View flight status/airport info/weather page for SouthWest OneWay

Meta:
@flow air
@process flight_status
@user anonymous
@traveler anonymous
@not_passing broken

Narrative:
In order to view single and multiple segment itineraries on the Check Flight Status Page
I enter departure-arrival one-way information
So that I can see a table displaying all flights matching my itinerary search

Scenario: Enter a SW-SW itinerary on the flight status results page
Given I am checking the flight status for a Southwest flight
And I am on the Check Flight Status page
When I check flight status
Then I should see itineraries matching my search on the flight status page
When I click a station with Airport Information
Then I see the Airport Information pop-up
When I select the link for Airport info
Then I see the Airport Arrival Times
