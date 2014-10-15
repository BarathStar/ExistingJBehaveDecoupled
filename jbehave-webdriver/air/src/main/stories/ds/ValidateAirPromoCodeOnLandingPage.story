Adult customer checks-in and receives drink coupon

Meta:
@project DS
@flow air
@process booking
@user
@traveler senior
@storyId DS-145

Purchase A Senior Air Ticket using a Multiuse promo code

Narrative:
In order to use a Multiuse promo code
As a senior
I want to book a flight using an valid and activated promo code and
verify that promo code is being applied and
receive a confirmation number

Scenario: senior air purchase round using a Multiuse promo code

Given I am flying a round-trip Southwest Southwest flight
When I book a flight
Then I receive an air confirmation number