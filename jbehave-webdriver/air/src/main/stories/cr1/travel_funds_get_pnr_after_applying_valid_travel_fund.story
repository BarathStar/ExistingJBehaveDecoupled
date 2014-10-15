Travel Funds

Meta:
@project cr1
@airTranOnly
@user Anonymous
@traveler adult
@flow air
@process booking
@storyId DCAIR4874
@dyna_stubs
@project_in_dev
@not_passing draft

Narrative:  As a User
I want to apply travel funds on the purchase page
so that the codeshare PNR is booked successfully

Scenario: Apply Travel Funds for FOP to book a codeshare PNR

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight with travel funds
Then I should receive an air confirmation
And I see travel funds applied
When I fill in the Purchase form and submit
Then I receive an air confirmation number
