Attempt to retrieve an itinerary using invalid PNR on the Add Rapid Rewards Page

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative: In order to see the OOPS message
As a anonymous user
I attempt to retrieve an Itinerary using an invalid PNR on the Add Rapid Rewards page


Scenario: Attempt to retrieve an Itinerary using an invalid PNR on the Add Rapid Rewards page (Scenario 5)
Given I am on the Add Rapid Rewards Number Page
When I attempt to retrieve an Itinerary using an invalid PNR on the Add Rapid Rewards page
Then I view the OOPS message for the invalid PNR on the Add Rapid Rewards page
