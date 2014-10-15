View flight status for AirTran/SouthWest round trip

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user anonymous
@storyId DCQA51
@dyna_stubs
@project_in_dev

Narrative:

In order to view single and multiple segment itineraries on the Check Flight Status Page
I enter departure-arrival round-trip information
So that I can see a table displaying all flights matching my itinerary search


Scenario: Enter a FL-SW itinerary on the flight status results page

Given I am checking the flight status for a FLWNCodeShare flight
And I am flying from BOS to ISP
And I am on the Check Flight Status page
When I check flight status
Then I should see itineraries matching my search on the flight status page
And I check operated by AirTran status
