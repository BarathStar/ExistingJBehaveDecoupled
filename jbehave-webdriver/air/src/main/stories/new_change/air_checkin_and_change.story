Change a already checked in anytime round-trip adult flight

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@not_passing DCAIR-6977 for fix

Narrative:
In order to receive my boarding pass and change my flight
As an adult
I want to checkin for a flight and then change it

Scenario: Adult Air Checkin and Change
Given I am flying a round-trip Southwest Southwest flight
When I book a flight eligible for checkin 1 adult
And I retrieve travel documents
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
Given I am on the Search Flight page
When I change the flight from search flight page
Then I should see the itinerary change confirmation page
