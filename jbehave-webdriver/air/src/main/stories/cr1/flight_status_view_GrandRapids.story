View flight status/airport info/weather page for Grand Rapids OneWay

Meta:
@project cr1
@flow air
@process view
@user anonymous
@dyna_stubs
@storyId DCQA-51, ZR-892
@not_passing draft

!-- drafted because this test fails in Selenium 2.7

Narrative:
As a customer
I want to search for Airtran flight on the Check Flight Status page
So that I can see the flight status and airport info for flights matching my itinerary search


Scenario: Enter a AirTran-Southwest itinerary on the flight status results page

Given I am flying a one-way AirTranOnly flight
And I am on the Check Flight Status page
When I check flight status
Then I should see itineraries matching my search on the flight status page
When I click a station with Airport Information
Then I see the Airport Information pop-up
When I select the link for Airport info
Then I see the Airport Arrival Times
When I am back on the flight status page
And I select the link for View Status
And I look up flight Details
Then I should see itineraries matching my search on the flight status page
When I click a station with Airport Information
Then I see the Airport Information pop-up
When I select the link for Airport info
Then I see the Airport Arrival Times
