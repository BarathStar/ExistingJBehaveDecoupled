Verify fare in Points option on the Select Flights Page as a RR member

Meta:
@flow rr
@process view
@user rr_member
@passenger adult
@not_live
@dyna_stubs

Narrative:
As a Rapid Rewards Member on My Account Snapshot page
In order to do a flight search
I want to verify that flights fares are shown in points if I choose it from search modal window
So that

Scenario: Verify Fare in points in Select Flights page

Given I am flying a round-trip Southwest Southwest flight
And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I search for flights with points from my account
Then I see selected the fares in points option on the Select Flights page