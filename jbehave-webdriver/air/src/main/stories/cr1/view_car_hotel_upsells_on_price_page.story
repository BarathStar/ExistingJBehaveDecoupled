Verify car and hotel up-sells are displayed on pricing page

Meta:
@project CR1
@flow air
@process view
@traveler adult
@user anonymous
@airTranOnly
@dyna_stubs
@storyId DCAIR-4909, ZR-899
@not_passing open jar step has not been implemented yet

Narrative:
As a customer
I want to book a flight with different combinations
So that I verify the car and hotel cross sells are displayed on price page.


Scenario: Viewing a WN Round Trip Itinerary
Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
Then I should see the correct pricing page
And I should see the hotel upsell widget
And I should see the car upsell widget
When I type ATL in the Pickup Location field
Then I should see Atlanta, GA - ATL as the first option in the auto-complete list


Scenario: Viewing a FL One Way Itinerary

Given I am flying a one-way AirTran flight
And I am a customer searching for one-way flights from the search flights page
When I select and view the Price page for a flight
Then I should see the correct pricing page
And I should see the hotel upsell widget
And I should see the car upsell widget
When I type ATL in the Pickup Location field
Then I should see Atlanta, GA - ATL as the first option in the auto-complete list


Scenario: Viewing an FL OpenJaw Itinerary

Given I am flying a open-jaw AirTran AirTran flight
And I am a customer searching for open-jaw flights from the search flights page
When I select and view the Price page for a flight
Then I should see the correct pricing page
And I should see the hotel upsell widget
And I should see the car upsell widget
When I type ATL in the Pickup Location field
Then I should see Atlanta, GA - ATL as the first option in the auto-complete list
