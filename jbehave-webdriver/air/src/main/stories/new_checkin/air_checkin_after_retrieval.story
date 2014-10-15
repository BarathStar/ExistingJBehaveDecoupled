Check-in round-trip anytime air ticket for an adult from retrieve itinerary page

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@not_passing draft

Narrative:
In order to receive my boarding pass
As an adult
I want to checkin for a flight after I retrieve it

Scenario: adult air view and checkin
Given I am flying a round-trip Southwest Southwest flight
When I book a flight eligible for checkin 1 adult
Then I receive an air confirmation number
When I retrieve my itinerary
Then I should see the correct view itinerary page
When I click on the Check in button on the view reservations Page
Then I view my boarding pass
