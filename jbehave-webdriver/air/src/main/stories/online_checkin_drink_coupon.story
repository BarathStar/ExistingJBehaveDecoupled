Adult customer checks-in and receives drink coupon

Meta:
@flow air
@process checking-in
@traveler adult
@not_passing draft
!-- To be undrafted after DCAIR-5514 is played


Narrative:
In order to get my drink on
As an adult customer
I want to receive my drink coupon when I check in

Scenario: Adult customer Online Checkin

Given I am flying a round-trip Southwest Southwest flight
When I book a flight
And I retrieve travel documents
And I select the drinks coupon checkbox
Then I view my boarding pass
!-- These "scenarios" can be combined
Given I am flying a round-trip Southwest Southwest flight
When I book a flight
And I retrieve travel documents
Then I view my boarding pass
And I should receive my drink coupon

Scenario: ODI Adult customer Online Checkin

Given I am flying a round-trip Southwest Southwest flight
And ODI Checkin On
When I book a flight
And I retrieve travel documents
And I select the drinks coupon checkbox
Then I view my boarding pass

Given I am flying a round-trip Southwest Southwest flight
When I book a flight
And I checkin for my flight
Then I view my boarding pass
And I should receive my drink coupon
