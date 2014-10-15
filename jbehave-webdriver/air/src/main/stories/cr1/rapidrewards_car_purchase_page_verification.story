Verify New Trip Element On Car Purchase Page

Meta:
@project tripManagement
@flow car
@process Information Search
@user rr_member


Narrative:



Scenario: Viewing an FL OpenJaw Itinerary

Given I am on the car reservation page logged in as a Rapid Rewards member
When I search and select a car and fill out information on the purchase page
Then the New Trip field is selected and visible
When I click add to existing trip
Then the Existing Trip field is selected and visible
