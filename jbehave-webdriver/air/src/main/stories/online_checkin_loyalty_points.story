Rapid Rewards Member check-in on flight booked with points

Meta:
@flow air
@process checking-in
@user rr_member
@not_passing draft

Narrative:
In order to get through security and board my plane
As a Rapid Rewards Member
I want to check-in for a flight that I've booked with points online, and receive a boarding pass

Scenario: Loyalty customer Points Fare online Checkin

Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I book a flight with points
Then I should receive an air confirmation
When I checkin for my flight
Then I should be checked in
And I should receive my drink coupon

Scenario: ODI Loyalty customer Points Fare online Checkin

Given I am flying a round-trip Southwest Southwest flight
And ODI Checkin On
And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I book a flight with points
Then I should receive an air confirmation
When I checkin for my flight
Then I should be checked in
And I should receive my drink coupon
