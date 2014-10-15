Attempt to Retrieve an Itinerary using an invalid PNR on the Check-in Air Reservation Page

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative: In order to see the OOPS message
As a user
I attempt to retrieve an Itinerary using an invalid PNR on the Check-in Air Reservation Page

Scenario: Attempt to Retrieve an Itinerary using an invalid PNR on the Check-in Air Reservation Page (Scenario 4)
Given I am on the check-in page
When I attempt to retrieve an itinerary using an invalid PNR on the Check-in Page
Then I view the OOPS message for the invalid PNR on the Check-in Page
