Verify fare in dollars as default option on the Select Flights Page as a RR member

Meta:
@flow rr
@process view
@user rr_member
@passenger adult
@not_live
@dyna_stubs
@not_passing delete me: story is only verifying dollars are selected by default on the bug page, other stories are checking this in the flow. "book_a_flight_with_luv_voucher.story"

Narrative:
As a Rapid Rewards Member on My Account Snapshot page
In order to do a flight search
I want to verify that fare in dollars is the default option on the Select Flights page
So that

Scenario: Fare in dollars as default option on the Select Flights page

Given I am flying a round-trip Southwest Southwest flight
And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I search for flights from my account
Then I see fares in dollars as default option on the Select Flights page