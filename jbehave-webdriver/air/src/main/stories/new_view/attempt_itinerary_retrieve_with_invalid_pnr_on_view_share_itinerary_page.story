Attempt to retrieve an Itinerary using an invalid PNR

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative: In order to see the OOPS message
As a user
I attempt to retrieve an Itinerary using an invalid PNR on the View/Share Itinerary page


Scenario: Attempt to Retrieve an Itinerary using an invalid PNR and click on the airtran.com link to transition to AirTran (Scenario 1)

Given I am on the view/share itinerary landing page
When I click on the continue button on the view reservation page
Then I see the oops messages indicating that all mandatory field were not entered retrieving a reservation by confirmation number
When I attempt to retrieve an itinerary using an invalid PNR from the View/Share Itinerary page
Then I view the OOPS message for the invalid PNR on the View/Share Itinerary page
