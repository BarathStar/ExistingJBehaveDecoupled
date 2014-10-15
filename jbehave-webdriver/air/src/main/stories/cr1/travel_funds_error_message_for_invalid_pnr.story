Travel Funds

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user Anonymous
@traveler adult
@storyId DCAIR4874
@dyna_stubs
@project_in_dev

Narrative:  As a user
I want to see the error message when invalid PNR applied in travel fund section on purchase page for codeshare flights
So that the travel fund has been validated

Scenario: Error message is displayed when user enters invalid PNR number in travel fund section

Given I am flying a round-trip AirTran SouthwestCodeshare flight
And I am on the purchase page
When I enter an invalid confirmation number as a travel fund
Then I see the invalid confirmation number error messages
