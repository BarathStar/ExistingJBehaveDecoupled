Attempt to retrieve Itinerary using an unassociated credit card on the View/Share Itinerary Page

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative: In order to see the OOPS message
As a user
I attempt to retrieve an itinerary using a credit card number, with a valid format,
 that is not associated with a passenger itinerary


Scenario: Retrieve an Invalid PNR with an unassociated credit card (Scenario 9)

Given I am on the view/share itinerary landing page
When I select the option Credit Card on the View Reservation Page
And I click on the continue button on the view reservation page
Then I see the oops messages indicating that all mandatory field were not entered for the Credit Card
When I attempt to retrieve an itinerary with an unassociated credit card
Then I see the OOPS message for the failing unassociated credit card PNR retrieval
