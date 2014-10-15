Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@not_passing draft


Narrative:
As a Swabiz Anonymous user
I want to make a purchase by entering a valid RR# Number with a space in between.
No CEM should be displayed since the App should be able to strip out the space and the Confirmation page should be displayed.

Scenario: Swabiz Anonymous user tries to make a purchase by entering a valid RR# Number with a space in between

Given I am flying a round-trip Southwest Southwest flight
And I have anonymously logged into a SWABiz account
When I search for a flight as a Rapid Rewards customer
And I select and purchase a flight with a RR# containing leading/trailing spaces
Then I should see the SWABiz confirmation page