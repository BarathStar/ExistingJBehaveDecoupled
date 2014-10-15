Verify I am directed to the Enter Rapid Rewards Number page with a AirTran/AirTran Round-Trip

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@user rr member
@dyna_stubs
@storyId DCQA-33, ZR-894
@not_passing draft


Narrative:
As a customer
I want to enter incorrect Passenger Name with Airtran PNR on Add rapid rewards page
So that I see the oops message for incorrect  Passenger Name.


Scenario: Viewing a FL_FL Itinerary

Given I am flying a round-trip AirTran AirTran flight
When I book a flight
Then I should see the confirmation page
And I click on the add Rapid Rewards number link
Given I am on the Add Rapid Rewards Number Page
When I enter my PNR and invalid Passenger Name on the add Rapid Rewards number page
Then I should see the oops message for invalid Passenger Name
