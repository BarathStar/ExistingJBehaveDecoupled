View flight status/airport info/weather page for ATL OneWay

Meta:
@project cr1
@flow air
@process view
@airTranOnly
@user anonymous
@dyna_stubs
@storyId DCQA-51, ZR-892
@not_passing draft

Narrative:
As a customer
I want to search with single and multiple segment itineraries on the Flight Schedules page
So that I can see the flight status, airport info and weather info for flights matching my itinerary search


Scenario: Enter a AirTran-Southwest itinerary on the flight status results page


Given I am flying a one-way CodeShare flight with a 1 stop segment
And I am flying from BOS to BWI
And I am on the flight schedule page
When I search for the flight schedule
Then I should see itineraries matching my search on the flight schedule page
When I click a station with Airport Information
Then I see the Airport Information pop-up
When I select the link for Airport info
Then I see the Airport Arrival Times
