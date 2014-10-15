Check AirTran Message on Add Rapid Rewards Number Page

Meta:
@project cr1
@flow air
@process booking
@user anonymous
@storyId DCQA33
@dyna_stubs
@project_in_dev

Narrative:
In order to see if AirTran A+ points can be used on SouthWest.com
As a customer
I want to Navigate to the Rapid Rewards Number Page

Scenario: AirTran Message appears when I try to add RR number
Given I am on the Search Flight page
When I click on manage reservation link
And I click on add Rapid Rewards from the View Itinerary Page
Then I view the AirTran Message on the Add Rapid Rewards Number Page
