Rapid Rewards Member checks-in to a flight purchased with dollars

Meta:
@flow air
@process checking-in
@user rr_member
@not_passing draft

Narrative:
In order to get through security and board my plane
As a Rapid Rewards Member
I want to check-in for a flight that I've booked with dollars online, and receive a boarding pass

Scenario: Loyalty customer Dollars Fare online Checkin

Given I am flying a round-trip Southwest Southwest flight
And I am on the Search Flight page
And I login as a Rapid Rewards Member
When I book a flight
And I checkin for my flight
Then I should be checked in
And I should receive my drink coupon

Scenario: ODI Loyalty customer Dollars Fare online Checkin

Given I am flying a round-trip Southwest Southwest flight
And ODI Checkin On
And I am on the Search Flight page
And I login as a Rapid Rewards Member
When I book a flight
And I checkin for my flight
Then I should be checked in
And I should receive my drink coupon
