View flight status for SouthWest/AirTran OneWay

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
So that I am reminded which portions of my trip are AirTran-operated
As a Southwest customer on the flight status page
I want to see information indicating which carrier is operating each flight segment

Scenario: Enter a WN-FL itinerary on the flight status results page

Given I am checking the flight status for a WNFLCodeShare flight
And I am flying from ISP to BOS
And I am on the Check Flight Status page
When I check flight status
Then I should see itineraries matching my search on the flight status page
And I check operated by AirTran status
