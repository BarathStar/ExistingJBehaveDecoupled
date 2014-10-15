Verify fare in dollars as default Option on My Account Snapshot as a RR member

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
I want to verify that fare in dollars is the default option in search modal window
So that

Scenario: Verify Fare in dollars as default option in search modal window

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select book a flight
Then I see fare in dollars as default option in modal window