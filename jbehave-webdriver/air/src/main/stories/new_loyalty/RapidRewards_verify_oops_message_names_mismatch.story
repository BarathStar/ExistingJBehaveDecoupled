Verify Oops Error Message during a flight booking when an Adult Passenger's Name and The Account Holder's Name do not match

Meta:
@flow rr
@process view
@user rr_member
@passenger adult
@not_live
@dyna_stubs

Narrative:
As a Rapid Rewards Member
In order to do a flight booking
I want to see an Oops error message when passenger name and the account holder's name are different
So that

Scenario: Verify an Oops error message when the names do no match

Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a valid Rapid Rewards member on the Search Flights page
And I am on the purchase page
When I fill out the purchase form
And I change the passenger's name
And I click on the Purchase button
Then I see the oops messages <msgs>

Examples:
|msgs|
|The passenger name you have entered does not match the name on file for this Rapid Rewards account # entered. If you are making a reservation for someone else, enter their Rapid Reward Account # or leave the Account # blank.|