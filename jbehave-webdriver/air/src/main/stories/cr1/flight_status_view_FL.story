View flight status for AirTran OneWay

Meta:
@project cr1
@flow air
@process view
@airTranOnly
@user anonymous
@dyna_stubs
@storyId DCQA-51, ZR-892
@project_in_dev

Narrative:
As a customer
I want to search for Airtran flight on the Check Flight Status page
So that I can see the flight status for flights matching my itinerary search


Scenario: Enter a FL-FL itinerary on the flight status results page

Given I am flying a one-way AirTran flight
And I am on the Check Flight Status page
When I check flight status
Then I should see itineraries matching my search on the flight status page
And I check operated by AirTran status

