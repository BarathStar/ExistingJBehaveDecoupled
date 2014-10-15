Attempt to Retrieve an Itinerary using an invalid PNR on the Cancel Air Reservation Page

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative: In order to see the OOPS message
As a user
I attempt to retrieve an Itinerary using an invalid PNR on the Cancel Air Reservation Page
and then click the airtran.com link to transition to AirTran.com

Scenario: Attempt to Retrieve an Itinerary using an invalid PNR on Cancel Air Reservation Page (Scenarios 3 and 12)
Given I am on the Cancel Air Reservation Page
When I attempt to retrieve an itinerary using an invalid PNR on the Cancel Air Reservation Page
Then I view the OOPS message for the invalid PNR on the Cancel Air Reservation Page
When I click the airtran.com link in the Oops message
Then I verify the transition to airtran.com
