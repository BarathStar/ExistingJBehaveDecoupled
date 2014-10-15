View a car reservation

Meta:
@flow car
@process retrieve
@user anonymous
@traveler adult
@not_passing draft
@message The method "I see the car itinerary" does not check anything and should be codified. The other methods are working well.

Narrative:
In order to view my car confirmation details
As an adult
I want to retrieve the car reservation using my confirmation number

Scenario: Customer Car Retrieve
Given I have booked Car products
When I retrieve the car itinerary
Then I see the car itinerary