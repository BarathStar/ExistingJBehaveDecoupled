Attempt to retrieve an Itinerary using an invalid PNR on the EarlyBird Check-in Page

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative: In order to see the OOPS message
As a user
I attempt to retrieve a invalid Itinerary using an invalid PNR on the EarlyBird Check-in flow landing page


Scenario: Attempt to Retrieve an Itinerary using an invalid PNR on the EarlyBird Check-in Page Flow Landing Page (Scenario 6)

Given I am on the early bird page
When I attempt to retrieve an itinerary using an invalid PNR on the EarlyBird Check-in Page
Then I view the OOPS message for the invalid PNR on the EarlyBird Check-in Page
