Complete a reservation change with airtran when using points

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@storyId DCAIR6413
@not_passing draft


Narrative:
In order to verify that I can change a reservation using Points
As a user
I have to complete a reservation change

Scenario:Complete reservation change using Points and verify confirmation page

Given I am flying a round-trip AirTran AirTran flight
And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I book a flight with points
Then I should receive an air confirmation
And I should see the confirmation page
When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
And I am redirected to AirTran to select my seat
